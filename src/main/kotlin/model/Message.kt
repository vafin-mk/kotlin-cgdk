package model

import java.util.Arrays

/**
 * This class describes a message, that master wizard can send to other wizards of his faction.
 *
 *
 * The message is sent personally to each wizard. Other wizards are unable to intercept him.
 *
 *
 * The recipient receives the message in the next game tick or later, depending on the size of the message.
 *
 *
 * The wizard is free to ignore as any part of the message and the entire message, however this can lead to the defeat
 * of wizard's faction.
 */
class Message(
    /**
     * @return the order to control the specified lane.
     */
    val lane: LaneType,
    /**
     * @return the order to learn the specified skill.
     * *
     * * This skill may require to learn other skills or be unavailable for learning at the moment due to the low level.
     * * The wizard should remember the order and move towards its achievement. The later the order, the higher the
     * * priority.
     * *
     *
     *
     * * The field value may not be available in all game modes.
     */
    val skillToLearn: SkillType, rawMessage: ByteArray) {
  private val rawMessage: ByteArray

  init {
    this.rawMessage = Arrays.copyOf(rawMessage, rawMessage.size)
  }

  /**
   * @return the text message in a forgotten ancient language.
   * *
   *
   *
   * * The maximal message length is `game.rawMessageMaxLength`. The speed of sending a message depends on its
   * * length. If the text part of the message is empty, the addressee will receive it in the next game tick. In other
   * * case, the time of receipt of the message will be delayed for
   * * `ceil(rawMessage.length / game.rawMessageTransmissionSpeed)` game ticks.
   * *
   *
   *
   * * The field value may not be available in all game modes.
   */
  fun getRawMessage(): ByteArray {
    return Arrays.copyOf(rawMessage, rawMessage.size)
  }
}
