import model.*

/**
 * This interface contains the methods that each wizard strategy should implement.
 */
interface Strategy {
  /**
   * Main strategy method, controlling the wizard.
   * The game engine calls this method once each time tick.

   * @param self  the wizard controlling by this strategy.
   * *
   * @param world the current world snapshot.
   * *
   * @param game  many game constants.
   * *
   * @param move  the object that encapsulates all strategy instructions to the `self` wizard.
   */
  fun move(self: Wizard, world: World, game: Game, move: Move)
}
