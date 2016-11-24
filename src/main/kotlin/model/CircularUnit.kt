package model

/**
 * This base class describes any circular unit in the game world.
 */
abstract class CircularUnit protected constructor(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction,
    /**
     * @return the radius of this unit.
     */
    val radius: Double) : Unit(id, x, y, speedX, speedY, angle, faction)
