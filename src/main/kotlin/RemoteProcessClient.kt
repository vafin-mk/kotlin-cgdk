import model.*
import java.io.*
import java.net.Socket
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets
import java.util.*
import java.io.IOException



class RemoteProcessClient @Throws(IOException::class)
constructor(host: String, port: Int) : Closeable {

  private val socket: Socket
  private val inputStream: InputStream
  private val outputStream: OutputStream
  private val outputStreamBuffer: ByteArrayOutputStream

  private var previousPlayers: Array<Player>? = null
  private var previousBuildings: Array<Building>? = null
  private var previousTrees: Array<Tree>? = null

  private val previousPlayerById = HashMap<Long, Player>()
  private val previousUnitById = HashMap<Long, model.Unit>()

  init {
    socket = Socket(host, port)
    socket.sendBufferSize = BUFFER_SIZE_BYTES
    socket.receiveBufferSize = BUFFER_SIZE_BYTES
    socket.tcpNoDelay = true

    inputStream = socket.inputStream
    outputStream = socket.outputStream
    outputStreamBuffer = ByteArrayOutputStream(BUFFER_SIZE_BYTES)
  }

  @Throws(IOException::class)
  fun writeToken(token: String) {
    writeEnum(MessageType.AUTHENTICATION_TOKEN)
    writeString(token)
    flush()
  }

  @Throws(IOException::class)
  fun writeProtocolVersion() {
    writeEnum(MessageType.PROTOCOL_VERSION)
    writeInt(3)
    flush()
  }

  @Throws(IOException::class)
  fun readTeamSize(): Int {
    ensureMessageType(readEnum(MessageType::class.java), MessageType.TEAM_SIZE)
    return readInt()
  }

  @Throws(IOException::class)
  fun readGameContext(): Game? {
    ensureMessageType(readEnum(MessageType::class.java), MessageType.GAME_CONTEXT)
    if (!readBoolean()) {
      return null
    }

    return Game(
        readLong(), readInt(), readDouble(), readBoolean(), readBoolean(), readDouble(), readDouble(),
        readDouble(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(), readInt(),
        readDouble(), readInt(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readDouble(), readDouble(), readInt(), readInt(), readInt(), readInt(), readDouble(), readDouble(),
        readDouble(), readDouble(), readDouble(), readInt(), readInt(), readInt(), readInt(), readInt(),
        readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(),
        readInt(), readDouble(), readDouble(), readIntArray()!!, readDouble(), readDouble(), readDouble(),
        readDouble(), readInt(), readInt(), readInt(), readInt(), readDouble(), readDouble(), readInt(),
        readDouble(), readDouble(), readDouble(), readInt(), readInt(), readDouble(), readDouble(), readInt(),
        readDouble(), readDouble(), readInt(), readDouble(), readDouble(), readInt(), readDouble(),
        readDouble(), readDouble(), readDouble(), readInt(), readInt(), readDouble(), readDouble(),
        readDouble(), readDouble(), readInt(), readInt(), readDouble(), readDouble(), readDouble(),
        readDouble(), readInt(), readInt(), readInt(), readInt(), readInt(), readDouble(), readInt(), readInt(),
        readDouble(), readDouble(), readDouble(), readInt(), readDouble(), readDouble(), readDouble(),
        readDouble(), readInt(), readInt(), readDouble(), readInt()
    )
  }

  @Throws(IOException::class)
  fun readPlayerContext(): PlayerContext? {
    val messageType = readEnum(MessageType::class.java)
    if (messageType == MessageType.GAME_OVER) {
      return null
    }

    ensureMessageType(messageType, MessageType.PLAYER_CONTEXT)
    return if (readBoolean()) PlayerContext(readWizards(), readWorld()!!) else null
  }

  @Throws(IOException::class)
  fun writeMoves(moves: Array<Move>) {
    writeEnum(MessageType.MOVES)
    writeArray(moves, { writeMove(it) })
    flush()
  }

  @Throws(IOException::class)
  private fun writeMove(move: Move?) {
    if (move == null) {
      writeBoolean(false)
    } else {
      writeBoolean(true)

      writeDouble(move.speed)
      writeDouble(move.strafeSpeed)
      writeDouble(move.turn)
      writeEnum(move.action)
      writeDouble(move.castAngle)
      writeDouble(move.minCastDistance)
      writeDouble(move.maxCastDistance)
      writeLong(move.statusTargetId)
      writeEnum(move.skillToLearn)
      writeMessages(move.getMessages())
    }
  }

  @Throws(IOException::class)
  override fun close() {
    socket.close()
  }

  @Throws(IOException::class)
  private fun readWorld(): World? {
    if (!readBoolean()) {
      return null
    }

    return World(
        readInt(), readInt(), readDouble(), readDouble(), readPlayers()!!, readWizards()!!, readMinions()!!,
        readProjectiles()!!, readBonuses()!!, readBuildings()!!, readTrees()!!
    )
  }

  @Throws(IOException::class)
  private fun readPlayers(): Array<Player>? {
    val players = readArray({ readPlayer()!! })
    if (players != null) {
      previousPlayers = players
    }
    return previousPlayers
  }

  @Throws(IOException::class)
  private fun readPlayer(): Player? {
    val flag = readBytes(1)[0]

    if (flag.toInt() == 0) {
      return null
    }

    if (flag.toInt() == 100) {
      return previousPlayerById[readLong()]
    }

    val player = Player(
        readLong(), readBoolean(), readString()!!, readBoolean(), readInt(), readEnum(Faction::class.java)!!
    )
    previousPlayerById.put(player.id, player)
    return player
  }

  @Throws(IOException::class)
  private fun readWizards(): Array<Wizard>? {
    return readArray({ readWizard()!! })
  }

  @Throws(IOException::class)
  private fun readWizard(): Wizard? {
    if (!readBoolean()) {
      return null
    }

    return Wizard(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readInt(), readInt(), readStatuses(), readLong(), readBoolean(),
        readInt(), readInt(), readDouble(), readDouble(), readInt(), readInt(), readEnumArray(SkillType::class.java)!!,
        readInt(), readIntArray(), readBoolean(), readMessages()
    )
  }

  @Throws(IOException::class)
  private fun readMinions(): Array<Minion>? {
    return readArray({ readMinion()!! })
  }

  @Throws(IOException::class)
  private fun readMinion(): Minion? {
    val flag = readBytes(1)[0]

    if (flag.toInt() == 0) {
      return null
    }

    if (flag.toInt() == 100) {
      return previousUnitById[readLong()] as Minion
    }

    val minion = Minion(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readInt(), readInt(), readStatuses(), readEnum(MinionType::class.java)!!,
        readDouble(), readInt(), readInt(), readInt()
    )
    previousUnitById.put(minion.id, minion)
    return minion
  }

  @Throws(IOException::class)
  private fun readProjectiles(): Array<Projectile>? {
    return readArray({ readProjectile()!! })
  }

  @Throws(IOException::class)
  private fun readProjectile(): Projectile? {
    if (!readBoolean()) {
      return null
    }

    return Projectile(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readEnum(ProjectileType::class.java)!!, readLong(), readLong()
    )
  }

  @Throws(IOException::class)
  private fun readBonuses(): Array<Bonus>? {
    return readArray({ this.readBonus()!! })
  }

  @Throws(IOException::class)
  private fun readBonus(): Bonus? {
    if (!readBoolean()) {
      return null
    }

    return Bonus(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readEnum(BonusType::class.java)!!
    )
  }

  @Throws(IOException::class)
  private fun readBuildings(): Array<Building>? {
    val buildings = readArray({ readBuilding()!! })
    if (buildings != null) {
      previousBuildings = buildings
    }
    return previousBuildings
  }

  @Throws(IOException::class)
  private fun readBuilding(): Building? {
    val flag = readBytes(1)[0]

    if (flag.toInt() == 0) {
      return null
    }

    if (flag.toInt() == 100) {
      return previousUnitById[readLong()] as Building
    }

    val building = Building(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readInt(), readInt(), readStatuses(),
        readEnum(BuildingType::class.java)!!, readDouble(), readDouble(), readInt(), readInt(), readInt()
    )
    previousUnitById.put(building.id, building)
    return building
  }

  @Throws(IOException::class)
  private fun readTrees(): Array<Tree>? {
    val trees = readArray({ readTree()!! })
    if (trees != null) {
      previousTrees = trees
    }
    return previousTrees
  }

  @Throws(IOException::class)
  private fun readTree(): Tree? {
    val flag = readBytes(1)[0]

    if (flag.toInt() == 0) {
      return null
    }

    if (flag.toInt() == 100) {
      return previousUnitById[readLong()] as Tree
    }

    val tree = Tree(
        readLong(), readDouble(), readDouble(), readDouble(), readDouble(), readDouble(),
        readEnum(Faction::class.java)!!, readDouble(), readInt(), readInt(), readStatuses()
    )
    previousUnitById.put(tree.id, tree)
    return tree
  }

  @Throws(IOException::class)
  private fun readMessages(): Array<Message> {
    return readArray({ readMessage()!! })!!
  }

  @Throws(IOException::class)
  private fun readMessage(): Message? {
    if (!readBoolean()) {
      return null
    }

    return Message(readEnum(LaneType::class.java)!!, readEnum(SkillType::class.java)!!, readByteArray(false)!!)
  }

  @Throws(IOException::class)
  private fun writeMessages(messages: Array<Message>?) {
    writeArray(messages, { writeMessage(it) })
  }

  @Throws(IOException::class)
  private fun writeMessage(message: Message?) {
    if (message == null) {
      writeBoolean(false)
    } else {
      writeBoolean(true)

      writeEnum(message.lane)
      writeEnum(message.skillToLearn)
      writeByteArray(message.getRawMessage())
    }
  }

  @Throws(IOException::class)
  private fun readStatuses(): Array<Status> {
    return readArray({readStatus()!!})!!
  }

  @Throws(IOException::class)
  private fun readStatus(): Status? {
    if (!readBoolean()) {
      return null
    }

    return Status(readLong(), readEnum(StatusType::class.java)!!, readLong(), readLong(), readInt())
  }

  private fun ensureMessageType(actualType: MessageType, expectedType: MessageType) {
    if (actualType != expectedType) {
      throw IllegalArgumentException(String.format(
          "Received wrong message [actual=%s, expected=%s].", actualType, expectedType
      ))
    }
  }

  inline @Throws(IOException::class)
  private fun <reified E> readArray(read: () -> E): Array<E>? {
    val length = readInt()
    if (length < 0) {
      return null
    }

    return Array(length, {i -> read()})
  }

  @Throws(IOException::class)
  private fun <E> writeArray(array: Array<E>?, write: (E) -> Unit) {
    if (array == null) {
      writeInt(-1)
    } else {
      val length = array.size
      writeInt(length)

      for (i in 0..length - 1) {
        write(array[i])
      }
    }
  }

  @Throws(IOException::class)
  private fun readByteArray(nullable: Boolean): ByteArray? {
    val count = readInt()

    if (nullable) {
      if (count < 0) {
        return null
      }
    } else {
      if (count <= 0) {
        return EMPTY_BYTE_ARRAY
      }
    }

    return readBytes(count)
  }

  @Throws(IOException::class)
  private fun writeByteArray(array: ByteArray?) {
    if (array == null) {
      writeInt(-1)
    } else {
      writeInt(array.size)
      writeBytes(array)
    }
  }

  @Throws(IOException::class)
  private fun <E : Enum<*>> readEnum(enumClass: Class<E>): E? {
    val ordinal = readBytes(1)[0]

    val values = enumClass.enumConstants
    val valueCount = values.size

    for (valueIndex in 0..valueCount - 1) {
      val value = values[valueIndex]
      if (value.ordinal == ordinal.toInt()) {
        return value
      }
    }

    return null
  }

  inline @Throws(IOException::class)
  private fun <reified E : Enum<*>> readEnumArray(enumClass: Class<E>, count: Int): Array<E> {
    val bytes = readBytes(count)

    val values = enumClass.enumConstants

    Arrays.sort(values) { valueA, valueB -> valueA.ordinal - valueB.ordinal }

    return Array(count, {i -> values[bytes[i].toInt()]})

  }

  inline @Throws(IOException::class)
  private fun <reified E : Enum<*>> readEnumArray(enumClass: Class<E>): Array<E>? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return readEnumArray(enumClass, count)
  }

  inline @Throws(IOException::class)
  private fun <reified E : Enum<*>> readEnumArray2D(enumClass: Class<E>): Array<Array<E>>? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return Array(count, {i -> readEnumArray(enumClass)!!})
  }

  @Throws(IOException::class)
  private fun <E : Enum<*>> writeEnum(value: E?) {
    writeByte(value?.ordinal ?: -1)
  }

  @Throws(IOException::class)
  private fun readString(): String? {
    val length = readInt()
    if (length == -1) {
      return null
    }

    return String(readBytes(length), StandardCharsets.UTF_8)
  }

  @Throws(IOException::class)
  private fun writeString(value: String?) {
    if (value == null) {
      writeInt(-1)
      return
    }

    val bytes = value.toByteArray(StandardCharsets.UTF_8)

    writeInt(bytes.size)
    writeBytes(bytes)
  }

  @Throws(IOException::class)
  private fun readBoolean(): Boolean {
    return readBytes(1)[0].toInt() != 0
  }

  @Throws(IOException::class)
  private fun readBooleanArray(count: Int): BooleanArray {
    val bytes = readBytes(count)
    val array = BooleanArray(count)

    for (i in 0..count - 1) {
      array[i] = bytes[i].toInt() != 0
    }

    return array
  }

  @Throws(IOException::class)
  private fun readBooleanArray(): BooleanArray? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return readBooleanArray(count)
  }

  @Throws(IOException::class)
  private fun readBooleanArray2D(): Array<BooleanArray>? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return Array(count, {i -> readBooleanArray()!!})
  }

  @Throws(IOException::class)
  private fun writeBoolean(value: Boolean) {
    writeByte(if (value) 1 else 0)
  }

  @Throws(IOException::class)
  private fun readInt(): Int {
    return ByteBuffer.wrap(readBytes(INTEGER_SIZE_BYTES)).order(PROTOCOL_BYTE_ORDER).int
  }

  @Throws(IOException::class)
  private fun readIntArray(count: Int): IntArray {
    val bytes = readBytes(count * INTEGER_SIZE_BYTES)
    val array = IntArray(count)

    for (i in 0..count - 1) {
      array[i] = ByteBuffer.wrap(
          bytes, i * INTEGER_SIZE_BYTES, INTEGER_SIZE_BYTES
      ).order(PROTOCOL_BYTE_ORDER).int
    }

    return array
  }

  @Throws(IOException::class)
  private fun readIntArray(): IntArray? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return readIntArray(count)
  }

  @Throws(IOException::class)
  private fun readIntArray2D(): Array<IntArray>? {
    val count = readInt()
    if (count < 0) {
      return null
    }

    return Array(count, {i -> readIntArray()!!})
  }

  @Throws(IOException::class)
  private fun writeInt(value: Int) {
    writeBytes(ByteBuffer.allocate(INTEGER_SIZE_BYTES).order(PROTOCOL_BYTE_ORDER).putInt(value).array())
  }

  @Throws(IOException::class)
  private fun readLong(): Long {
    return ByteBuffer.wrap(readBytes(LONG_SIZE_BYTES)).order(PROTOCOL_BYTE_ORDER).long
  }

  @Throws(IOException::class)
  private fun writeLong(value: Long) {
    writeBytes(ByteBuffer.allocate(LONG_SIZE_BYTES).order(PROTOCOL_BYTE_ORDER).putLong(value).array())
  }

  @Throws(IOException::class)
  private fun readDouble(): Double {
    return java.lang.Double.longBitsToDouble(readLong())
  }

  @Throws(IOException::class)
  private fun writeDouble(value: Double) {
    writeLong(java.lang.Double.doubleToLongBits(value))
  }

  @Throws(IOException::class)
  private fun readBytes(byteCount: Int): ByteArray {
    val bytes = ByteArray(byteCount)
    var offset = 0
    var readByteCount: Int

    while (offset < byteCount) {
      readByteCount = inputStream.read(bytes, offset, byteCount - offset)
      if (readByteCount == -1) {
        break
      }
      offset += readByteCount
    }

    if (offset != byteCount) {
      throw IOException(String.format("Can't read %d bytes from input stream.", byteCount))
    }

    return bytes
  }

  @Throws(IOException::class)
  private fun writeBytes(bytes: ByteArray) {
    outputStreamBuffer.write(bytes)
  }

  @Throws(IOException::class)
  private fun writeByte(value : Int) {
    try {
      outputStreamBuffer.write(value)
    } catch (e : Exception) {
      throw IOException("Can't write a byte into output stream.", e)
    }
  }

  @Throws(IOException::class)
  private fun flush() {
    outputStream.write(outputStreamBuffer.toByteArray())
    outputStreamBuffer.reset()
    outputStream.flush()
  }

  private enum class MessageType {
    UNKNOWN,
    GAME_OVER,
    AUTHENTICATION_TOKEN,
    TEAM_SIZE,
    PROTOCOL_VERSION,
    GAME_CONTEXT,
    PLAYER_CONTEXT,
    MOVES
  }

  companion object {
    private val BUFFER_SIZE_BYTES = 1 shl 20
    private val PROTOCOL_BYTE_ORDER = ByteOrder.LITTLE_ENDIAN
    private val INTEGER_SIZE_BYTES = Integer.SIZE / java.lang.Byte.SIZE
    private val LONG_SIZE_BYTES = java.lang.Long.SIZE / java.lang.Byte.SIZE

    private val EMPTY_BYTE_ARRAY = ByteArray(0)

    private fun ensureMessageType(actualType: MessageType?, expectedType: MessageType?) {
      if (actualType != expectedType) {
        throw IllegalArgumentException(String.format(
            "Received wrong message [actual=%s, expected=%s].", actualType, expectedType
        ))
      }
    }
  }
}