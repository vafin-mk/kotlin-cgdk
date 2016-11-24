package model

import java.util.Arrays

/**
 * This class describes a game world. A world contains all players and game objects (``units'').
 */
class World(
    /**
     * @return the current game tick.
     */
    val tickIndex: Int,
    /**
     * @return the base game duration in ticks. A real game duration may be lower. Equals to `game.tickCount`.
     */
    val tickCount: Int,
    /**
     * @return the world width.
     */
    val width: Double,
    /**
     * @return the world height.
     */
    val height: Double, players: Array<Player>, wizards: Array<Wizard>,
    minions: Array<Minion>, projectiles: Array<Projectile>, bonuses: Array<Bonus>, buildings: Array<Building>, trees: Array<Tree>) {
  private val players: Array<Player>
  private val wizards: Array<Wizard>
  private val minions: Array<Minion>
  private val projectiles: Array<Projectile>
  private val bonuses: Array<Bonus>
  private val buildings: Array<Building>
  private val trees: Array<Tree>

  init {
    this.players = Arrays.copyOf(players, players.size)
    this.wizards = Arrays.copyOf(wizards, wizards.size)
    this.minions = Arrays.copyOf(minions, minions.size)
    this.projectiles = Arrays.copyOf(projectiles, projectiles.size)
    this.bonuses = Arrays.copyOf(bonuses, bonuses.size)
    this.buildings = Arrays.copyOf(buildings, buildings.size)
    this.trees = Arrays.copyOf(trees, trees.size)
  }

  /**
   * @return all players (in random order). After each tick the player objects are recreated.
   */
  fun getPlayers(): Array<Player> {
    return Arrays.copyOf(players, players.size)
  }

  /**
   * @return visible wizards (in random order). After each tick the wizard objects are recreated.
   */
  fun getWizards(): Array<Wizard> {
    return Arrays.copyOf(wizards, wizards.size)
  }

  /**
   * @return visible minions (in random order). After each tick the minion objects are recreated.
   */
  fun getMinions(): Array<Minion> {
    return Arrays.copyOf(minions, minions.size)
  }

  /**
   * @return visible projectiles (in random order). After each tick the projectile objects are recreated.
   */
  fun getProjectiles(): Array<Projectile> {
    return Arrays.copyOf(projectiles, projectiles.size)
  }

  /**
   * @return visible bonuses (in random order). After each tick the bonus objects are recreated.
   */
  fun getBonuses(): Array<Bonus> {
    return Arrays.copyOf(bonuses, bonuses.size)
  }

  /**
   * @return visible buildings (in random order). After each tick the building objects are recreated.
   */
  fun getBuildings(): Array<Building> {
    return Arrays.copyOf(buildings, buildings.size)
  }

  /**
   * @return visible trees (in random order). After each tick the tree objects are recreated.
   */
  fun getTrees(): Array<Tree> {
    return Arrays.copyOf(trees, trees.size)
  }

  /**
   * @return your player.
   */
  val myPlayer: Player?
    get() {
      for (playerIndex in players.indices.reversed()) {
        val player = players[playerIndex]
        if (player.isMe) {
          return player
        }
      }

      return null
    }
}
