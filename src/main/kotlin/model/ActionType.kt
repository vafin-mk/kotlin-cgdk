package model

/**
 * Available wizard actions.
 *
 *
 * A wizard can not perform any new action, if he is not yet recovered from his previous action
 * (`wizard.remainingActionCooldownTicks` is greater than `0`).
 *
 *
 * A wizard can not perform the specific new action, if it is not yet recovered from its previous usage
 * (`remainingCooldownTicksByAction[actionType.ordinal()]` is greater than `0`).
 */
enum class ActionType {
  /**
   * Do nothing.
   */
  NONE,

  /**
   * Perform a melee attack with a staff.
   *
   *
   * This attack damages all living units in a sector of `-game.staffSector / 2.0` to
   * `game.staffSector / 2.0`. The distance between wizard and target centers should not exceed
   * `game.staffRange + livingUnit.radius`.
   */
  STAFF,

  /**
   * Cast a magic missile.
   *
   *
   * Magic missile is a basic spell of any wizard. Inflicts some damage upon a direct hit.
   *
   *
   * The center of a newly created magic missile is the same as the center of a caster wizard. The angle of a
   * projectile is equal to `wizard.angle + move.castAngle`, and its speed is `game.magicMissileSpeed`.
   * All collisions between a projectile and its caster are ignored by the game engine.
   *
   *
   * Requires `game.magicMissileManacost` manapoints.
   */
  MAGIC_MISSILE,

  /**
   * Cast a frost bolt.
   *
   *
   * A frost bolt inflicts some damage upon a direct hit and freezes a target.
   *
   *
   * The center of a newly created frost bolt is the same as the center of a caster wizard. The angle of a
   * projectile is equal to `wizard.angle + move.castAngle`, and its speed is `game.frostBoltSpeed`.
   * All collisions between a projectile and its caster are ignored by the game engine.
   *
   *
   * Requires the `FROST_BOLT` skill and `game.frostBoltManacost` manapoints.
   */
  FROST_BOLT,

  /**
   * Cast a fireball.
   *
   *
   * A fireball explodes when reaching maximal cast range or upon a collision with living unit.
   * Damages and burns all living units nearby.
   *
   *
   * The center of a newly created fireball is the same as the center of a caster wizard. The angle of a
   * projectile is equal to `wizard.angle + move.castAngle`, and its speed is `game.fireballSpeed`.
   * All collisions between a projectile and its caster are ignored by the game engine.
   *
   *
   * Requires the `FIREBALL` skill and `game.fireballManacost` manapoints.
   */
  FIREBALL,

  /**
   * Cast a haste spell, that temporarily speedups the friendly wizard with ID equal to `move.statusTargetId`
   * or the caster himself if the game engine can not find such wizard.
   *
   *
   * Requires the `HASTE` skill and `game.hasteManacost` manapoints.
   */
  HASTE,

  /**
   * Cast a shield spell, that temporarily protects the friendly wizard with ID equal to `move.statusTargetId`
   * or the caster himself if the game engine can not find such wizard.
   *
   *
   * Requires the `SHIELD` skill and `game.shieldManacost` manapoints.
   */
  SHIELD
}
