package model

/**
 * This class is inherited from a living unit and describes a tree.
 */
class Tree(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    life: Int, maxLife: Int, statuses: Array<Status>) : LivingUnit(id, x, y, speedX, speedY, angle, faction, radius, life, maxLife, statuses)
