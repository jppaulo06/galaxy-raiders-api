package galaxyraiders.core.score

open class Leaderboard :
  Board (fileName = "Leaderboard") {

  override fun updateSaves() {
    val newSave = Save(this.date, this.score)
    this.saves = this.saves + newSave
    this.saves = this.saves.sortedByDescending { it.score }.take(3)
  }
}
