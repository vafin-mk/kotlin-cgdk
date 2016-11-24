import model.*

class MyStrategy : Strategy {
  override fun move(self: Wizard, world: World, game: Game, move: Move) {
    move.speed = game.wizardForwardSpeed
    move.strafeSpeed = game.wizardStrafeSpeed
    move.turn = game.wizardMaxTurnAngle
    move.action = ActionType.MAGIC_MISSILE
  }
}
