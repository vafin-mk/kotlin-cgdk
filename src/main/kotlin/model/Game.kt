package model

import java.util.Arrays

/**
 * An instance of this class contains all game constants.
 */
class Game(
    /**
     * @return the number that your strategy may use to initialize a generator of random numbers.
     */
    val randomSeed: Long,
    /**
     * @return the base game duration in ticks. A real game duration may be lower. Equals to `world.tickCount`.
     */
    val tickCount: Int,
    /**
     * @return the size (both width and height) of the map.
     */
    val mapSize: Double,
    /**
     * @return `true` if and only if the wizards in this game can gain new levels and learn skills.
     */
    val isSkillsEnabled: Boolean,
    /**
     * @return `true` if and only if the master wizards in this game can send raw messages.
     */
    val isRawMessagesEnabled: Boolean,
    /**
     * @return Returns the damage part dealt by the wizards one faction to each other as a result of friendly fire.
     * *
     *
     *
     * * The value depends on the game mode, but is always in range of `0.0` to `1.0`.
     * *
     *
     *
     * * Regardless of the game mode, wizards can not damage friendly minions and buildings.
     */
    val friendlyFireDamageFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for the damage dealt to the opposite faction
     * * buildings.
     */
    val buildingDamageScoreFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for destroying the opposite faction building.
     * *
     *
     *
     * * Applies to the maximal amount of building's hitpoints.
     */
    val buildingEliminationScoreFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for the damage dealt to the other faction
     * * minions.
     */
    val minionDamageScoreFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for killing the other faction minion.
     * *
     *
     *
     * * Applies to the maximal amount of minion's hitpoints.
     */
    val minionEliminationScoreFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for the damage dealt to the opposite faction
     * * wizards.
     */
    val wizardDamageScoreFactor: Double,
    /**
     * @return the factor of the experience points gained by the wizard for killing the opposite faction wizard.
     * *
     *
     *
     * * Applies to the maximal amount of wizard's hitpoints.
     */
    val wizardEliminationScoreFactor: Double,
    /**
     * @return the experience multiplier applied in case, if the enemy unit died near two or more friendly wizards.
     * *
     *
     *
     * * After applying this multiplier, the amount of the experience is rounded down.
     */
    val teamWorkingScoreFactor: Double,
    /**
     * @return the amount of experience points received by each player of the faction in case of victory.
     */
    val victoryScore: Int,
    /**
     * @return the maximal range, at which a wizard gains experience points in case, if a friendly unit kills an other
     * * faction unit.
     * *
     *
     *
     * * The experience is evenly distributed between all the wizards not farther than `scoreGainRange` from a
     * * killed unit, and the killer unit if he is also a wizard.
     * *
     *
     *
     * * If the damage is not fatal, this parameter is not used. If the attacker is a wizard, than the experience
     * * completely goes to this wizard. If the attacker is a minion or a building, nobody gains an experience.
     * *
     *
     *
     * * The range is considered as the range between units' centers.
     */
    val scoreGainRange: Double,
    /**
     * @return the maximal possible length of a raw message.
     * *
     *
     *
     * * If a message has higher length, then it will be completely ignored.
     */
    val rawMessageMaxLength: Int,
    /**
     * @return the raw message transmission speed.
     * *
     *
     *
     * * If the raw message is empty, the addressee will receive it in the next game tick. In other case, the time of
     * * receipt of the message will be delayed for `ceil(message.rawMessage.length / rawMessageTransmissionSpeed)`
     * * game ticks.
     */
    val rawMessageTransmissionSpeed: Double,
    /**
     * @return the radius of a wizard.
     */
    val wizardRadius: Double,
    /**
     * @return the base cast range of a wizard.
     * *
     *
     *
     * * The effective cast range (`wizard.castRange`) may be higher depending on skills of the wizard and auras of
     * * nearby friendly wizards.
     */
    val wizardCastRange: Double,
    /**
     * @return the maximal range (between units' centers), at which a wizard can detect other units.
     */
    val wizardVisionRange: Double,
    /**
     * @return the base limit of wizard's forward speed.
     * *
     *
     *
     * * The effective forward speed may be higher depending on skills of the wizard and auras of nearby friendly
     * * wizards. The `HASTENED` status can also greatly speed up a wizard.
     */
    val wizardForwardSpeed: Double,
    /**
     * @return the base limit of wizard's backward speed.
     * *
     *
     *
     * * The effective backward speed may be higher depending on skills of the wizard and auras of nearby friendly
     * * wizards. The `HASTENED` status can also greatly speed up a wizard.
     */
    val wizardBackwardSpeed: Double,
    /**
     * @return the base limit of wizard's strafe speed.
     * *
     *
     *
     * * The effective strafe speed may be higher depending on skills of the wizard and auras of nearby friendly
     * * wizards. The `HASTENED` status can also greatly speed up a wizard.
     */
    val wizardStrafeSpeed: Double,
    /**
     * @return the maximal amount of wizard's hitpoints at initial level.
     */
    val wizardBaseLife: Int,
    /**
     * @return the growth of wizard's hitpoints per level.
     */
    val wizardLifeGrowthPerLevel: Int,
    /**
     * @return the maximal amount of wizard's manapoints at initial level.
     */
    val wizardBaseMana: Int,
    /**
     * @return the growth of wizard's manapoints per level.
     */
    val wizardManaGrowthPerLevel: Int,
    /**
     * @return the regeneration speed of wizard's hitpoints at initial level.
     */
    val wizardBaseLifeRegeneration: Double,
    /**
     * @return the growth of the regeneration speed of wizard's hitpoints.
     */
    val wizardLifeRegenerationGrowthPerLevel: Double,
    /**
     * @return the regeneration speed of wizard's manapoints at initial level.
     */
    val wizardBaseManaRegeneration: Double,
    /**
     * @return the growth of the regeneration speed of wizard's manapoints.
     */
    val wizardManaRegenerationGrowthPerLevel: Double,
    /**
     * @return the base limit of wizard's turn speed.
     * *
     *
     *
     * * The `HASTENED` status increases this limit by `1.0 + hastenedRotationBonusFactor` times.
     */
    val wizardMaxTurnAngle: Double,
    /**
     * @return the maximal possible delay of a wizard's revival.
     */
    val wizardMaxResurrectionDelayTicks: Int,
    /**
     * @return the minimal possible delay of a wizard's revival.
     */
    val wizardMinResurrectionDelayTicks: Int,
    /**
     * @return the minimal possible interval between any two actions of a wizard.
     */
    val wizardActionCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two staff attacks.
     */
    val staffCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two ``Magic missile'' spell casts.
     */
    val magicMissileCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two ``Frost bolt'' spell casts.
     */
    val frostBoltCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two ``Fireball'' spell casts.
     */
    val fireballCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two ``Haste'' spell casts.
     */
    val hasteCooldownTicks: Int,
    /**
     * @return the minimal possible interval between any two ``Shield'' spell casts.
     */
    val shieldCooldownTicks: Int,
    /**
     * @return the ``Magic missile'' spell manacost.
     */
    val magicMissileManacost: Int,
    /**
     * @return the ``Frost bolt'' spell manacost.
     */
    val frostBoltManacost: Int,
    /**
     * @return the ``Fireball'' spell manacost.
     */
    val fireballManacost: Int,
    /**
     * @return the ``Haste'' spell manacost.
     */
    val hasteManacost: Int,
    /**
     * @return the ``Shield'' spell manacost.
     */
    val shieldManacost: Int,
    /**
     * @return the base staff damage.
     * *
     *
     *
     * * The effective damage may be higher depending on skills of the wizard and auras of nearby friendly wizards.
     */
    val staffDamage: Int,
    /**
     * @return the wizard's staff sector.
     * *
     *
     *
     * * A staff attack damages all living units in a sector of `-staffSector / 2.0` to `staffSector / 2.0`.
     * * This also applies to the status spells and to the relative projectile angle.
     */
    val staffSector: Double,
    /**
     * @return the wizard's staff range.
     * *
     *
     *
     * * A staff attack damages all living units if the distance between wizard's and target's centers is not greater than
     * * `staffRange + livingUnit.radius`.
     */
    val staffRange: Double, levelUpXpValues: IntArray,
    /**
     * @return the radius of a minion.
     */
    val minionRadius: Double,
    /**
     * @return the maximal range (between units' centers), at which a minion can detect other units.
     */
    val minionVisionRange: Double,
    /**
     * @return the forward speed of a minion.
     * *
     *
     *
     * * A minion can not strafe or move backward.
     */
    val minionSpeed: Double,
    /**
     * @return the maximal turn speed of a minion.
     */
    val minionMaxTurnAngle: Double,
    /**
     * @return the maximal amount of minion's hitpoints.
     */
    val minionLife: Int,
    /**
     * @return the interval at which appear the minions of the two opposing factions (`ACADEMY` and
     * * `RENEGADES`).
     * *
     *
     *
     * * The minions of each of these factions appear in three groups near their base (one group per lane). Each group
     * * consists of three orcs and one fetish. The minions immediately begin to advance on their lane toward the opposite
     * * faction base, attacking all enemies in their path.
     */
    val factionMinionAppearanceIntervalTicks: Int,
    /**
     * @return the minimal possible interval between any two attacks of an orc.
     */
    val orcWoodcutterActionCooldownTicks: Int,
    /**
     * @return the damage of one attack of an orc.
     */
    val orcWoodcutterDamage: Int,
    /**
     * @return the orc's axe sector.
     * *
     *
     *
     * * An axe attack damages all living units in a sector of `-orcWoodcutterAttackSector / 2.0` to
     * * `orcWoodcutterAttackSector / 2.0`.
     */
    val orcWoodcutterAttackSector: Double,
    /**
     * @return the orc's axe range.
     * *
     *
     *
     * * An axe attack damages all living units if the distance between orc's and target's centers is not greater than
     * * `orcWoodcutterAttackRange + livingUnit.radius`.
     */
    val orcWoodcutterAttackRange: Double,
    /**
     * @return the minimal possible interval between any two attacks of a fetish.
     */
    val fetishBlowdartActionCooldownTicks: Int,
    /**
     * @return the maximal dart fly range.
     */
    val fetishBlowdartAttackRange: Double,
    /**
     * @return the dart throw sector.
     * *
     *
     *
     * * The relative angle of a dart is in range of `-fetishBlowdartAttackSector / 2.0` to
     * * `fetishBlowdartAttackSector / 2.0`.
     */
    val fetishBlowdartAttackSector: Double,
    /**
     * @return the radius of a bonus.
     */
    val bonusRadius: Double,
    /**
     * @return the interval at which appears a bonus.
     * *
     *
     *
     * * Every $2500$ ticks up to two bonuses may appear on the map. The bonuses are created at the two following points:
     * * (`mapSize * 0.3`, `mapSize * 0.3`) and (`mapSize * 0.7`, `mapSize * 0.7`). If any part of
     * * the new bonus area is already occupied by a wizard or by an existing bonus, then the creation of the bonus will
     * * be postponed till the end of the next interval.
     */
    val bonusAppearanceIntervalTicks: Int,
    /**
     * @return the amount of score and experience points for taking a bonus.
     */
    val bonusScoreAmount: Int,
    /**
     * @return the radius of a dart.
     */
    val dartRadius: Double,
    /**
     * @return the dart speed.
     */
    val dartSpeed: Double,
    /**
     * @return the dart damage.
     */
    val dartDirectDamage: Int,
    /**
     * @return the radius of a magic missile projectile.
     */
    val magicMissileRadius: Double,
    /**
     * @return the magic missile projectile speed.
     */
    val magicMissileSpeed: Double,
    /**
     * @return the magic missile projectile damage.
     */
    val magicMissileDirectDamage: Int,
    /**
     * @return the radius of a frost bolt projectile.
     */
    val frostBoltRadius: Double,
    /**
     * @return the frost bolt projectile speed.
     */
    val frostBoltSpeed: Double,
    /**
     * @return the frost bolt projectile damage.
     */
    val frostBoltDirectDamage: Int,
    /**
     * @return the radius of a fireball projectile.
     */
    val fireballRadius: Double,
    /**
     * @return the fireball projectile speed.
     */
    val fireballSpeed: Double,
    /**
     * @return the radius of the area in which living units are taking maximal damage from the fireball projectile
     * * explosion.
     * *
     * @see .getFireballExplosionMaxDamage
     */
    val fireballExplosionMaxDamageRange: Double,
    /**
     * @return the radius of the area in which living units are taking any damage from the fireball projectile
     * * explosion.
     * *
     * @see .getFireballExplosionMaxDamage
     */
    val fireballExplosionMinDamageRange: Double,
    /**
     * @return the damage of the fireball at the epicenter of the explosion.
     * *
     *
     *
     * * A living unit takes the `fireballExplosionMaxDamage` if the distance from the center of the explosion to
     * * the nearest point of this unit does not exceed the `fireballExplosionMaxDamageRange`. As you increase the
     * * distance to the `fireballExplosionMinDamageRange`, the damage of the fireball decreases in a linear manner
     * * and reaches `fireballExplosionMinDamage`. If the distance from the center of the explosion to the nearest
     * * point of the living unit exceeds `fireballExplosionMinDamageRange`, this unit takes no damage.
     * *
     *
     *
     * * If a living unit takes any damage from the fireball explosion, it receives a `BURNING` status.
     */
    val fireballExplosionMaxDamage: Int,
    /**
     * @return the damage of the fireball on the periphery of the explosion.
     * *
     * @see .getFireballExplosionMaxDamage
     */
    val fireballExplosionMinDamage: Int,
    /**
     * @return the radius of a guardian tower.
     */
    val guardianTowerRadius: Double,
    /**
     * @return the maximal range (between units' centers), at which a guardian tower can detect other units.
     */
    val guardianTowerVisionRange: Double,
    /**
     * @return the maximal amount of guardian tower's hitpoints.
     */
    val guardianTowerLife: Double,
    /**
     * @return the maximal range (between units' centers), at which a guardian tower can attack other units.
     */
    val guardianTowerAttackRange: Double,
    /**
     * @return the damage of one attack of a guardian tower.
     */
    val guardianTowerDamage: Int,
    /**
     * @return the minimal possible interval between any two attacks of a guardian tower.
     */
    val guardianTowerCooldownTicks: Int,
    /**
     * @return the radius of a faction base.
     */
    val factionBaseRadius: Double,
    /**
     * @return the maximal range (between units' centers), at which a faction base can detect other units.
     */
    val factionBaseVisionRange: Double,
    /**
     * @return the maximal amount of faction base's hitpoints.
     */
    val factionBaseLife: Double,
    /**
     * @return the maximal range (between units' centers), at which a faction base can attack other units.
     */
    val factionBaseAttackRange: Double,
    /**
     * @return the damage of one attack of a faction base.
     */
    val factionBaseDamage: Int,
    /**
     * @return the minimal possible interval between any two attacks of a faction base.
     */
    val factionBaseCooldownTicks: Int,
    /**
     * @return the duration of the `BURNING` status.
     */
    val burningDurationTicks: Int,
    /**
     * @return the total damage of the `BURNING` status.
     */
    val burningSummaryDamage: Int,
    /**
     * @return the duration of the `EMPOWERED` status.
     */
    val empoweredDurationTicks: Int,
    /**
     * @return the damage multiplier of empowered living unit. DOT (damage over time) is excluded.
     */
    val empoweredDamageFactor: Double,
    /**
     * @return the duration of the `FROZEN` status.
     */
    val frozenDurationTicks: Int,
    /**
     * @return the duration of the `HASTENED` status.
     */
    val hastenedDurationTicks: Int,
    /**
     * @return the `HASTENED` status duration multiplier (in case of taking a bonus).
     */
    val hastenedBonusDurationFactor: Double,
    /**
     * @return the relative move speed boost of a hastened unit.
     * *
     *
     *
     * * The maximal possible wizard speed is
     * * `1.0 + 4.0 * movementBonusFactorPerSkillLevel + hastenedMovementBonusFactor` of the base speed.
     */
    val hastenedMovementBonusFactor: Double,
    /**
     * @return the relative turn speed boost of a hastened unit.
     */
    val hastenedRotationBonusFactor: Double,
    /**
     * @return the `SHIELDED` duration.
     */
    val shieldedDurationTicks: Int,
    /**
     * @return the `SHIELDED` status duration multiplier (in case of taking a bonus).
     */
    val shieldedBonusDurationFactor: Double,
    /**
     * @return the damage part absorbed by shield. DOT (damage over time) is excluded.
     */
    val shieldedDirectDamageAbsorptionFactor: Double,
    /**
     * @return the range of an aura skill.
     */
    val auraSkillRange: Double,
    /**
     * @return the absolute increase of the wizard cast range for each learned skill, which is one of the
     * * prerequisites of the `ADVANCED_MAGIC_MISSILE` skill.
     */
    val rangeBonusPerSkillLevel: Double,
    /**
     * @return the absolute increase of the wizard spell damage for each learned skill, which is one of the
     * * prerequisites of the `FROST_BOLT` skill. DOT (damage over time) is excluded.
     */
    val magicalDamageBonusPerSkillLevel: Int,
    /**
     * @return the absolute increase of the wizard staff damage for each learned skill, which is one of the
     * * prerequisites of the `FIREBALL` skill.
     */
    val staffDamageBonusPerSkillLevel: Int,
    /**
     * @return the relative increase of the move speed for each learned skill, which is one of the
     * * prerequisites of the `HASTE` skill.
     * *
     *
     *
     * * The maximal possible wizard speed is
     * * `1.0 + 4.0 * movementBonusFactorPerSkillLevel + hastenedMovementBonusFactor` of the base speed.
     */
    val movementBonusFactorPerSkillLevel: Double,
    /**
     * @return the absolute decrease of the incoming magical damage for each learned skill, which is one of the
     * * prerequisites of the `SHIELD` skill. DOT (damage over time) is excluded.
     */
    val magicalDamageAbsorptionPerSkillLevel: Int) {
  private val levelUpXpValues: IntArray

  init {
    this.levelUpXpValues = Arrays.copyOf(levelUpXpValues, levelUpXpValues.size)
  }

  /**
   * @return the non-negative integers.
   * *
   *
   *
   * * The numbers of items is equal to the number of levels a wizard can gain. An item `N` mean a number of
   * * experience points a wizard of level `N` should get to reach the next level. Thus, the amount of experience
   * * required for the zero level wizard to get to the level `N`, is the sum of the first `N` elements.
   */
  fun getLevelUpXpValues(): IntArray {
    return if (levelUpXpValues.size == 0) levelUpXpValues else Arrays.copyOf(levelUpXpValues, levelUpXpValues.size)
  }
}
