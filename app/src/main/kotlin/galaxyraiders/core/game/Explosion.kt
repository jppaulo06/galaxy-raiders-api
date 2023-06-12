package galaxyraiders.core.game

import galaxyraiders.Config

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

object ExplosionConfig {
  private val config = Config("GR__CORE__GAME__EXPLOSION__")

  val explosionTime = config.get<Int>("EXPLOSION_TIME")
  val explosionRadiusMultiplier = config.get<Int>("EXPLOSION_RADIUS_MULTIPLIER")
}

class Explosion(
    var asteroid: Asteroid
    ) :
  SpaceObject(
      "Explosion",
      'X',
      asteroid.center,
      asteroid.velocity,
      ExplosionConfig.explosionRadiusMultiplier * asteroid.radius,
      asteroid.mass
  ) {

  private var time = 0

  fun increaseTime() {
    this.time++
  }

  fun timeHasNotEnded(): Boolean {
    return this.time <= ExplosionConfig.explosionTime
  }

  fun move () {
    super.move(this.asteroid.velocity)
  }
}
