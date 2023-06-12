package galaxyraiders.core.score

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import galaxyraiders.Config
import galaxyraiders.core.game.Asteroid

import java.io.File
import java.nio.file.Paths
import java.time.LocalDateTime

object BoardConfig {
  val config = Config("GR__CORE__SCORE__BOARD__")

  val path = config.get<String>("PATH")
}

abstract class Board (
  val fileName: String,
) {

  protected data class Save (var date: String, var score: Double)

  val file: File = File(BoardConfig.path + "/" + this.fileName + ".json")

  protected var saves: List<Save> = emptyList()

  var date = LocalDateTime.now().toString()
  var score: Double = 0.0

  fun addScore(asteroid: Asteroid) {
    this.score += asteroid.score
  }

  init {
    this.defineFile()
    this.readSavedData()

    val shutDownSaving = Thread {
      this.save()
    }

    Runtime.getRuntime().addShutdownHook(shutDownSaving)
  }

  open protected fun defineFile () {
    this.file.createNewFile()
  }

  open protected fun readSavedData() {
    val text = this.file!!.readText()
    val itemType = object : TypeToken<List<Save>>() {}.type
    this.saves = Gson().fromJson<List<Save>>(text, itemType) ?: emptyList()
  }

  open protected fun save() {
    this.updateSaves()
    val text = Gson().toJson(this.saves)
    this.file!!.writeText(text)
  }

  protected abstract fun updateSaves()
}
