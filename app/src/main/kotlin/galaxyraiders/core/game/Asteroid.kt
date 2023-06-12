package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Asteroid(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("Asteroid", '.', initialPosition, initialVelocity, radius, mass) {

  private var exploded: Boolean = false

  val score = this.mass * this.radius

  fun explode () {
    this.exploded = true
  }
}
