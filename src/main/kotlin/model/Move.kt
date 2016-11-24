package model

import java.util.Arrays

/**
 * An encapsulated result of each move of your strategy.
 */
class Move {
  /**
   * @return the current move speed.
   */
  /**
   * Sets move speed for one tick.
   *
   *
   * By default the speed is in range of `-game.wizardBackwardSpeed` to `game.wizardForwardSpeed`.
   * These limits can be extended depending on skills of moving wizard and auras of nearby friendly wizards.
   * The `HASTENED` status can also greatly speed up a wizard.
   *
   *
   * If a specified value is out of the range, than it become equal to the nearest value of the range.
   * The positive values mean moving forward.
   *
   *
   * If the value `hypot(speed / maxSpeed, strafeSpeed / maxStrafeSpeed)` is greater than `1.0`, than both
   * `speed` and `strafeSpeed` will be divided by this value.
   */
  var speed: Double = 0.toDouble()
  /**
   * @return the current strafe speed.
   */
  /**
   * Sets the strafe speed for one tick.
   *
   *
   * By default the strafe speed is in range of `-game.wizardStrafeSpeed` to `game.wizardStrafeSpeed`.
   * These limits can be extended depending on skills of moving wizard and auras of nearby friendly wizards.
   * The `HASTENED` status can also greatly speed up a wizard.
   *
   *
   * If a specified value is out of the range, than it become equal to the nearest value of the range.
   * The positive values mean moving to the right.
   *
   *
   * If the value `hypot(speed / maxSpeed, strafeSpeed / maxStrafeSpeed)` is greater than `1.0`, than both
   * `speed` and `strafeSpeed` will be divided by this value.
   */
  var strafeSpeed: Double = 0.toDouble()
  /**
   * @return the current turn angle.
   */
  /**
   * Sets the turn angle for one tick.
   *
   *
   * The turn angle is in radians and is relative to the current angle of the wizard.
   * By default the turn angle is in range of `-game.wizardMaxTurnAngle` to `game.wizardMaxTurnAngle`.
   * The `HASTENED` status increases bot limits by `1.0 + game.hastenedRotationBonusFactor` times.
   *
   *
   * If a specified value is out of the range, than it become equal to the nearest value of the range.
   * The positive values mean turning clockwise.
   */
  var turn: Double = 0.toDouble()
  /**
   * @return the current wizard action.
   */
  /**
   * Sets the action for one tick.
   *
   *
   * The specified action can be ignored by the game engine, if the controlling wizard has insufficient manapoints or
   * this action is on cooldown.
   */
  var action: ActionType? = null
  /**
   * @return the current cast angle.
   */
  /**
   * Sets the cast angle for one tick.
   *
   *
   * The cast angle is in radians and is relative to the current angle of the wizard.
   * The cast angle is in range of `-game.staffSector / 2.0` to `game.staffSector / 2.0`.
   *
   *
   * If a specified value is out of the range, than it become equal to the nearest value of the range.
   * The positive values mean turning clockwise.
   *
   *
   * If the specified action is not a projectile spell, than the game engine will simply ignore this parameter.
   */
  var castAngle: Double = 0.toDouble()
  /**
   * @return the current minimal cast distance.
   */
  /**
   * Sets the minimal cast distance for one tick.
   *
   *
   * If the distance from the center of the projectile to the point of its occurrence is less than the value of this
   * parameter, the battle properties of the projectile are ignored. The projectile passes freely through all other
   * game objects, except trees.
   *
   *
   * Default value is `0.0`. All collisions between a projectile and its caster are ignored by the game engine.
   *
   *
   * If the specified action is not a projectile spell, than the game engine will simply ignore this parameter.
   */
  var minCastDistance: Double = 0.toDouble()
  /**
   * @return the current maximal cast distance.
   */
  /**
   * Sets the maximal cast distance for one tick.
   *
   *
   * If the distance from the center of the projectile to the point of its occurrence is greater than the value of
   * this parameter, the projectile will be removed from the game world. In this case, the `FIREBALL` projectile
   * detonates.
   *
   *
   * The default value of this parameter is higher than the maximal flying range of any projectile in the game.
   *
   *
   * If the specified action is not a projectile spell, than the game engine will simply ignore this parameter.
   */
  var maxCastDistance = 10000.0
  /**
   * @return the current ID of the status spell target.
   */
  /**
   * Sets the ID of the target living unit to cast a status spell.
   *
   *
   * According to the game rules, the valid targets are only the wizards of the same faction. If the wizard with the
   * specified ID is not found, the status is applied directly to the wizard performing the action. The relative angle
   * to the target should be in range of `-game.staffSector / 2.0` to `game.staffSector / 2.0`. The
   * distance to the target is limited by `wizard.castRange`.
   *
   *
   * The default value of this parameter is `-1` (wrong ID).
   *
   *
   * If the specified action is not a status spell, than the game engine will simply ignore this parameter.
   */
  var statusTargetId = -1L
  /**
   * @return the currently selected skill to learn.
   */
  /**
   * Selects the skill to learn before the start of the next tick.
   *
   *
   * The setting will be ignored by the game engine if the current wizard level is less than or equal to the number of
   * the already learned skills. Some skills may also require learning other skills.
   *
   *
   * In some game modes a wizard can not learn skills.
   */
  var skillToLearn: SkillType? = null
  private var messages: Array<Message>? = null

  /**
   * @return the current messages for friendly wizards.
   */
  fun getMessages(): Array<Message>? {
    return if (messages == null) null else Arrays.copyOf(messages, messages!!.size)
  }

  /**
   * Sets the messages for the wizards of the same faction.
   *
   *
   * Available only to the master wizard. If not empty, the number of messages must be strictly equal to the number of
   * wizards of the friendly faction (dead or alive) except the master wizard.
   *
   *
   * Messages are addressed in ascending order of wizard IDs. Some messages can be empty (`null`), if supported
   * by the programming language used by strategy. In other case all items should be the correct messages.
   *
   *
   * The game engine may ignore the message to a specific wizard, if there is another pending message to the same
   * wizard. If the addressed wizard is dead, then the message will be removed from the game world and the wizard will
   * never get it.
   *
   *
   * Not all game modes support the messages.
   */
  fun setMessages(messages: Array<Message>?) {
    this.messages = if (messages == null) null else Arrays.copyOf(messages, messages.size)
  }
}
