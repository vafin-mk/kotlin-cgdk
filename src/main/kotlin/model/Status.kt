package model

/**
 * A magical status, affecting living unit.
 */
class Status(
    /**
     * @return the unique status ID.
     */
    val id: Long,
    /**
     * @return the status type.
     */
    val type: StatusType,
    /**
     * @return the ID of the wizard casted this status or `-1`.
     */
    val wizardId: Long,
    /**
     * @return the ID of the player, which unit casted this status, or `-1`.
     */
    val playerId: Long,
    /**
     * @return the remaining status duration.
     */
    val remainingDurationTicks: Int)
