package galaxyraiders.core.score

class Scoreboard :
  Board (fileName = "Scoreboard") {

  override fun updateSaves() {
    val newSave = Save(this.date, this.score)
    this.saves = this.saves + newSave
  }
}
