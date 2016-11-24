package model

/**
 * The instance of this class contains all the data about player state.
 */
class Player(
    /**
     * @return the unique player ID.
     */
    val id: Long,
    /**
     * @return `true` if and only if this is your player.
     */
    val isMe: Boolean,
    /**
     * @return the name of the player.
     */
    val name: String,
    /**
     * @return `true` if and only if the strategy of this player is crashed.
     */
    val isStrategyCrashed: Boolean,
    /**
     * @return the amount of score points.
     */
    val score: Int,
    /**
     * @return the faction of this player.
     */
    val faction: Faction)
