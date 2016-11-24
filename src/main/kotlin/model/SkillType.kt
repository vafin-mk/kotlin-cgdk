package model

/**
 * Skill type. In some game modes a wizard can not learn skills (see `game.skillsEnabled`).
 *
 *
 * There is three skill groups: active, passive and auras.
 *
 *  *
 * Active skills provide an ability to perform a new action, not available before.
 *
 *  *
 * Passive skills are constantly improving some characteristic of the wizard for a certain value.
 *
 *  *
 * Auras are constantly improving some characteristic of the wizard and all friendly wizards in the
 * `game.auraSkillRange`.
 *
 *
 */
enum class SkillType {
  /**
   * Passive skill. Increases cast range by `game.rangeBonusPerSkillLevel`.
   */
  RANGE_BONUS_PASSIVE_1,

  /**
   * Aura. Increases cast range by `game.rangeBonusPerSkillLevel`.
   *
   *
   * Requires `RANGE_BONUS_PASSIVE_1`.
   */
  RANGE_BONUS_AURA_1,

  /**
   * Passive skill. Increases cast range by `2.0 * game.rangeBonusPerSkillLevel`.
   *
   *
   * Requires `RANGE_BONUS_AURA_1`.
   */
  RANGE_BONUS_PASSIVE_2,

  /**
   * Aura. Increases cast range by `2.0 * game.rangeBonusPerSkillLevel`.
   *
   *
   * Requires `RANGE_BONUS_PASSIVE_2`.
   */
  RANGE_BONUS_AURA_2,

  /**
   * Passive skill. Removes the `MAGIC_MISSILE` spell delay.
   * The common action delay `game.wizardActionCooldownTicks` still applies.
   *
   *
   * Requires `RANGE_BONUS_AURA_2`.
   */
  ADVANCED_MAGIC_MISSILE,

  /**
   * Passive skill. Increases instant magical damage by `game.magicalDamageBonusPerSkillLevel`.
   */
  MAGICAL_DAMAGE_BONUS_PASSIVE_1,

  /**
   * Aura. Increases instant magical damage by `game.magicalDamageBonusPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_BONUS_PASSIVE_1`.
   */
  MAGICAL_DAMAGE_BONUS_AURA_1,

  /**
   * Passive skill. Increases instant magical damage by `2.0 * game.magicalDamageBonusPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_BONUS_AURA_1`.
   */
  MAGICAL_DAMAGE_BONUS_PASSIVE_2,

  /**
   * Aura. Increases instant magical damage by `2.0 * game.magicalDamageBonusPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_BONUS_PASSIVE_2`.
   */
  MAGICAL_DAMAGE_BONUS_AURA_2,

  /**
   * Active skill. A wizard can now use the `FROST_BOLT` spell.
   *
   *
   * Requires `MAGICAL_DAMAGE_BONUS_AURA_2`.
   */
  FROST_BOLT,

  /**
   * Passive skill. Increases staff damage by `game.staffDamageBonusPerSkillLevel`.
   */
  STAFF_DAMAGE_BONUS_PASSIVE_1,

  /**
   * Aura. Increases staff damage by  `game.staffDamageBonusPerSkillLevel`.
   *
   *
   * Requires `STAFF_DAMAGE_BONUS_PASSIVE_1`.
   */
  STAFF_DAMAGE_BONUS_AURA_1,

  /**
   * Passive skill. Increases staff damage by `2.0 * game.staffDamageBonusPerSkillLevel`.
   *
   *
   * Requires `STAFF_DAMAGE_BONUS_AURA_1`.
   */
  STAFF_DAMAGE_BONUS_PASSIVE_2,

  /**
   * Aura. Increases staff damage by `2.0 * game.staffDamageBonusPerSkillLevel`.
   *
   *
   * Requires `STAFF_DAMAGE_BONUS_PASSIVE_2`.
   */
  STAFF_DAMAGE_BONUS_AURA_2,

  /**
   * Active skill. A wizard can now use the `FIREBALL` spell.
   *
   *
   * Requires `STAFF_DAMAGE_BONUS_AURA_2`.
   */
  FIREBALL,

  /**
   * Passive skill. Increases movement speed by `1.0 + game.movementBonusFactorPerSkillLevel` times.
   *
   *
   * Summarily `MOVEMENT_BONUS_FACTOR_PASSIVE_2` and `MOVEMENT_BONUS_FACTOR_AURA_2` increase movement
   * speed by `1.0 + 4.0 * game.movementBonusFactorPerSkillLevel` times.
   */
  MOVEMENT_BONUS_FACTOR_PASSIVE_1,

  /**
   * Aura. Increases movement speed by `1.0 + game.movementBonusFactorPerSkillLevel` times.
   *
   *
   * Requires `MOVEMENT_BONUS_FACTOR_PASSIVE_1`.
   */
  MOVEMENT_BONUS_FACTOR_AURA_1,

  /**
   * Passive skill. Increases movement speed by `1.0 + 2.0 * game.movementBonusFactorPerSkillLevel` times.
   *
   *
   * Requires `MOVEMENT_BONUS_FACTOR_AURA_1`.
   */
  MOVEMENT_BONUS_FACTOR_PASSIVE_2,

  /**
   * Aura. Increases movement speed by `1.0 + 2.0 * game.movementBonusFactorPerSkillLevel` times.
   *
   *
   * Requires `MOVEMENT_BONUS_FACTOR_PASSIVE_2`.
   */
  MOVEMENT_BONUS_FACTOR_AURA_2,

  /**
   * Active skill. A wizard can now use the `HASTE` spell.
   *
   *
   * Requires `MOVEMENT_BONUS_FACTOR_AURA_2`.
   */
  HASTE,

  /**
   * Passive skill. Decreases received magical damage by `game.magicalDamageAbsorptionPerSkillLevel`.
   */
  MAGICAL_DAMAGE_ABSORPTION_PASSIVE_1,

  /**
   * Aura. Decreases received magical damage by `game.magicalDamageAbsorptionPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_ABSORPTION_PASSIVE_1`.
   */
  MAGICAL_DAMAGE_ABSORPTION_AURA_1,

  /**
   * Passive skill. Decreases received magical damage by `2.0 * game.magicalDamageAbsorptionPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_ABSORPTION_AURA_1`.
   */
  MAGICAL_DAMAGE_ABSORPTION_PASSIVE_2,

  /**
   * Aura. Decreases received magical damage by `2.0 * game.magicalDamageAbsorptionPerSkillLevel`.
   *
   *
   * Requires `MAGICAL_DAMAGE_ABSORPTION_PASSIVE_2`.
   */
  MAGICAL_DAMAGE_ABSORPTION_AURA_2,

  /**
   * Active skill. A wizard can now use the `SHIELD` spell.
   *
   *
   * Requires `MAGICAL_DAMAGE_ABSORPTION_AURA_2`.
   */
  SHIELD
}
