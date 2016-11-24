package model

import java.util.Arrays

/**
 * This class is inherited from a living unit and describes a wizard.
 */
class Wizard(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    life: Int, maxLife: Int, statuses: Array<Status>,
    /**
     * @return the ID of the owner player.
     */
    val ownerPlayerId: Long,
    /**
     * @return `true` if and only if this wizard is your.
     */
    val isMe: Boolean,
    /**
     * @return the current amount of manapoints.
     */
    val mana: Int,
    /**
     * @return the maximal amount of manapoints.
     */
    val maxMana: Int,
    /**
     * @return the maximal range (between units' centers), at which this wizard can detect other units.
     */
    val visionRange: Double,
    /**
     * @return the maximal cast range..
     */
    val castRange: Double,
    /**
     * @return the current amount of experience points.
     */
    val xp: Int,
    /**
     * @return the current wizard level.
     * *
     *
     *
     * * Each wizard starts at level `0` and can level up up to `game.levelUpXpValues.length` times.
     * *
     *
     *
     * * In some game modes a wizard can not gain new levels.
     */
    val level: Int, skills: Array<SkillType>,
    /**
     * @return the amount of ticks remaining before any new action.
     * *
     *
     *
     * * A wizard can perform the action `actionType` if and only if both `remainingActionCooldownTicks` and
     * * `remainingCooldownTicksByAction[actionType.ordinal()]` are equal to zero.
     */
    val remainingActionCooldownTicks: Int, remainingCooldownTicksByAction: IntArray?,
    /**
     * @return `true` if and only if this wizard is master.
     * *
     *
     *
     * * There is exactly one master wizard per faction.
     */
    val isMaster: Boolean,
    messages: Array<Message>?) : LivingUnit(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses) {
  private val skills: Array<SkillType>
  private val remainingCooldownTicksByAction: IntArray
  private val messages: Array<Message>

  init {
    this.skills = Arrays.copyOf(skills, skills.size)
    this.remainingCooldownTicksByAction = if (remainingCooldownTicksByAction == null)
      IntArray(0)
    else
      Arrays.copyOf(remainingCooldownTicksByAction, remainingCooldownTicksByAction.size)
    this.messages = if (messages == null) arrayOf() else Arrays.copyOf(messages, messages.size)
  }

  /**
   * @return the skills of this wizard.
   */
  fun getSkills(): Array<SkillType> {
    return Arrays.copyOf(skills, skills.size)
  }

  /**
   * @return the non-negative integer numbers. Each item is equal to the amount of ticks remaining before the next
   * * action with the corresponding index.
   * *
   *
   *
   * * For example, `remainingCooldownTicksByAction[0]` corresponds to `NONE` action and always equal to
   * * zero. `remainingCooldownTicksByAction[1]` corresponds to `STAFF` action and equal to the amount of
   * * ticks remaining before the next staff attack.
   * *
   *
   *
   * * A wizard can perform the action `actionType` if and only if both `remainingActionCooldownTicks` and
   * * `remainingCooldownTicksByAction[actionType.ordinal()]` are equal to zero.
   */
  fun getRemainingCooldownTicksByAction(): IntArray {
    return Arrays.copyOf(remainingCooldownTicksByAction, remainingCooldownTicksByAction.size)
  }

  /**
   * @return the messages addressed to this wizard.
   * *
   *
   *
   * * A strategy can only read messages of the controlling wizard.
   */
  fun getMessages(): Array<Message> {
    return Arrays.copyOf(messages, messages.size)
  }
}
