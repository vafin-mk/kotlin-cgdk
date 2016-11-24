package model

/**
 * Bonus type.
 *
 *
 * Besides the primary effect each taken bonus gives `game.bonusScoreAmount` score points to the player. The
 * wizard gains the same amount as xp.
 */
enum class BonusType {
  /**
   * Dramatically increases the damage of ranged and melee attacks for some time.
   */
  EMPOWER,

  /**
   * Grants the `HASTENED` status to the wizard.
   *
   *
   * Duration of the status is longer than usually.
   */
  HASTE,

  /**
   * Grants the `SHIELDED` status to the wizard.
   *
   *
   * Duration of the status is longer than usually.
   */
  SHIELD
}
