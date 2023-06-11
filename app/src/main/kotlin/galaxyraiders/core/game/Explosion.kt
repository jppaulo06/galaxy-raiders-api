package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

const val EXPLOSION_TIME: Int = 20

const val EXPLOSION_RADIUS_MULTIPLIER = 2

class Explosion(
    var asteroid: Asteroid
    ) :
  SpaceObject(
      "Explosion",
      'X',
      asteroid.center,
      asteroid.velocity,
      EXPLOSION_RADIUS_MULTIPLIER * asteroid.radius,
      asteroid.mass
  ) {

  private var time = 0

  fun increaseTime() {
    this.time++
  }

  fun timeHasNotEnded(): Boolean {
    return this.time <= EXPLOSION_TIME
  }

  fun move () {
    super.move(this.asteroid.velocity)
  }
}
