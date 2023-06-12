package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@DisplayName("Given an asteroid")
class ExplosionTest {
  private val asteroid = Asteroid(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 1.0,
    mass = 1.0
  )

  private val explosion = Explosion(this.asteroid)

  @Test
  fun `it has a type Explosion`() {
    assertEquals("Explosion", explosion.type)
  }

  @Test
  fun `it has a symbol X`() {
    assertEquals('X', explosion.symbol)
  }

  @Test
  fun `it shows the type Explosion when converted to String `() {
    assertTrue(explosion.toString().contains("Explosion"))
  }

  @Test
  fun `it has the same position of the asteroid`() {
    assertEquals(explosion.center, asteroid.center)
  }
}

