package model

/**
 * This class describes a building. Faction building automatically attack a random enemy in a certain range.
 *
 *
 * A building can not be (`FROZEN`).
 */
class Building(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    life: Int, maxLife: Int, statuses: Array<Status>,
    /**
     * @return the building type.
     */
    val type: BuildingType,
    /**
     * @return the maximal range (between units' centers), at which this building can detect other units.
     */
    val visionRange: Double,
    /**
     * @return the maximal range (between units' centers), at which this building can attack other units.
     */
    val attackRange: Double,
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
