import model.*
import java.io.IOException

fun main(args : Array<String>) {
  Runner(if (args.size === 3) args else arrayOf("127.0.0.1", "31001", "0000000000000000")).run()
}

class Runner @Throws(IOException::class)
constructor(args: Array<String>) {
  private val remoteProcessClient: RemoteProcessClient
  private val token: String

  init {
    remoteProcessClient = RemoteProcessClient(args[0], Integer.parseInt(args[1]))
    token = args[2]
  }

  @Throws(IOException::class)
  fun run() = try {
    remoteProcessClient.writeToken(token)
    remoteProcessClient.writeProtocolVersion()
    val teamSize = remoteProcessClient.readTeamSize()
    val game = remoteProcessClient.readGameContext()

    val strategies = Array<Strategy>(teamSize, {i -> MyStrategy()})
    var playerContext: PlayerContext?

    while (true) {
      playerContext = remoteProcessClient.readPlayerContext()
      if (playerContext == null) {
        break
      }
      val playerWizards = playerContext.getWizards()
      if (playerWizards == null || playerWizards.size != teamSize) {
        break
      }

      val moves = Array(teamSize, {i -> Move()})

      for (wizardIndex in 0..teamSize - 1) {
        val playerWizard = playerWizards[wizardIndex]

        strategies[wizardIndex /*playerWizard.getTeammateIndex()*/].move(
            playerWizard, playerContext.world, game!!, moves[wizardIndex]
        )
      }

      remoteProcessClient.writeMoves(moves)
    }
  } finally {
    remoteProcessClient.close()
  }
}
