package model

/**
 * This class is inherited from a circular unit and describes a projectile.
 */
class Projectile(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    /**
     * @return the type of the projectile.
     */
    val type: ProjectileType,
    /**
     * @return the ID of the unit created this projectile.
     */
    val ownerUnitId: Long,
    /**
     * @return the ID of the player, which unit created this projectile, or `-1`.
     */
    val ownerPlayerId: Long) : CircularUnit(id, x, y, speedX, speedY, angle, faction, radius)
