package model

import java.util.Arrays

/**
 * This base class is inherited from a circular unit and describes any living unit in the game world.
 */
abstract class LivingUnit protected constructor(
    id: Long, x: Double, y: Double, speedX: Double, speedY: Double, angle: Double, faction: Faction, radius: Double,
    /**
     * @return the current amount of hitpoints.
     */
    val life: Int,
    /**
     * @return the maximal amount of hitpoints.
     */
    val maxLife: Int, statuses: Array<Status>) : CircularUnit(id, x, y, speedX, speedY, angle, faction, radius) {
  private val statuses: Array<Status>

  init {
    this.statuses = Arrays.copyOf(statuses, statuses.size)
  }

  /**
   * @return the magical statuses affecting this living unit.
   */
  fun getStatuses(): Array<Status> {
    return Arrays.copyOf(statuses, statuses.size)
  }
}
