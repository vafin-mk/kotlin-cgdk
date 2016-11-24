package model

/**
 * This class describes a bonus. Bonus is a static useful circular unit.
 */
class Bonus(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    /**
     * @return the bonus type.
     */
    val type: BonusType) : CircularUnit(id, x, y, speedX, speedY, angle, faction, radius)
