package model


import java.lang.StrictMath.PI
import java.lang.StrictMath.atan2
import java.lang.StrictMath.hypot

/**
 * Base class that describes any object (``unit'') in the game world.
 */
abstract class Unit protected constructor(
    /**
     * @return the unique unit ID.
     */
    val id: Long,
    /**
     * @return the X of the unit's center. The X-axis is directed from left to right.
     */
    val x: Double,
    /**
     * @return the Y of the unit's center. The Y-axis is directed downward.
     */
    val y: Double,
    /**
     * @return the X speed component or the last tick X-coordinate change, if this unit can instantly change its speed.
     * * The X-axis is directed from left to right.
     */
    val speedX: Double,
    /**
     * @return the Y speed component or the last tick Y-coordinate change, if this unit can instantly change its speed.
     * * The Y-axis is directed downward.
     */
    val speedY: Double,
    /**
     * @return the turn angle in radians of this unit. Direction of the X-axis has zero angle.
     * * Positive angle corresponds to the rotation in a clockwise direction.
     */
    val angle: Double,
    /**
     * @return the faction of this unit.
     */
    val faction: Faction) {

  /**
   * @param x X-coordinate of the point to get the angle to.
   * *
   * @param y Y-coordinate of the point to get the angle to.
   * *
   * @return the relative angle to the specified point.
   * * The angle is in range of `-PI` to `PI` both inclusive.
   */
  fun getAngleTo(x: Double, y: Double): Double {
    val absoluteAngleTo = atan2(y - this.y, x - this.x)
    var relativeAngleTo = absoluteAngleTo - angle

    while (relativeAngleTo > PI) {
      relativeAngleTo -= 2.0 * PI
    }

    while (relativeAngleTo < -PI) {
      relativeAngleTo += 2.0 * PI
    }

    return relativeAngleTo
  }

  /**
   * @param unit the unit to get the angle to.
   * *
   * @return the relative angle to the center of the specified unit.
   * * The angle is in range of `-PI` to `PI` both inclusive.
   */
  fun getAngleTo(unit: Unit): Double {
    return getAngleTo(unit.x, unit.y)
  }

  /**
   * @param x X-coordinate of the point to get the distance to.
   * *
   * @param y Y-coordinate of the point to get the distance to.
   * *
   * @return the range between the specified point and the center of this unit.
   */
  fun getDistanceTo(x: Double, y: Double): Double {
    return hypot(x - this.x, y - this.y)
  }

  /**
   * @param unit the unit to get the distance to.
   * *
   * @return the range between the center of the specified unit and the center of this unit.
   */
  fun getDistanceTo(unit: Unit): Double {
    return getDistanceTo(unit.x, unit.y)
  }
}
