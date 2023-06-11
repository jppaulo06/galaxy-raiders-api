package galaxyraiders.core.score

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import galaxyraiders.Config

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

  protected data class Save (var date: String, var score: Int)

  var file: File? = null

  protected var saves: List<Save> = emptyList()

  var date = LocalDateTime.now().toString()
  var score = 0

  init {
    this.defineFile()
    this.readSavedData()

    val shutDownSaving = Thread {
      this.save()
    }

    Runtime.getRuntime().addShutdownHook(shutDownSaving)
  }

  fun addScore() {
    this.score += 1
  }

  protected abstract fun updateSaves()

  private fun defineFile () {
    this.file = File(BoardConfig.path + "/" + this.fileName + ".json")
  }

  private fun readSavedData() {
    val text = this.file!!.readText()
    val itemType = object : TypeToken<List<Save>>() {}.type
    this.saves = Gson().fromJson<List<Save>>(text, itemType)
  }

  private fun save() {
    this.updateSaves()
    val text = Gson().toJson(this.saves)
    println("Salvando" + text )
    this.file!!.writeText(text)
  }
}
