package model

import java.util.Arrays

class PlayerContext(wizards: Array<Wizard>?, val world: World) {
  private val wizards: Array<Wizard>?

  init {
    this.wizards = if (wizards == null) null else Arrays.copyOf(wizards, wizards.size)
  }

  fun getWizards(): Array<Wizard>? {
    return if (wizards == null) null else Arrays.copyOf(wizards, wizards.size)
  }
}
