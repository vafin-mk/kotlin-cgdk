package model

/**
 * This class is inherited from a living unit and describes a minion.
 */
class Minion(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    life: Int, maxLife: Int, statuses: Array<Status>,
    /**
     * @return the minion type.
     */
    val type: MinionType,
    /**
     * @return the maximal range (between units' centers), at which this minion can detect other units.
     */
    val visionRange: Double,
    /**
     * @return the damage of one attack.
     */
    val damage: Int,
    /**
     * @return the delay between attacks.
     */
    val cooldownTicks: Int,
    /**
     * @return the amount of ticks remaining before the next attack.
     */
    val remainingActionCooldownTicks: Int) : LivingUnit(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses)
