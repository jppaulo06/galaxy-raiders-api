package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.sqrt
import kotlin.math.atan
import kotlin.math.PI
import kotlin.math.abs

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt(this * this)

  val radiant: Double
    get() {
      val r = atan(Math.abs(dy) / Math.abs(dx))
      if(dx < 0 && dy < 0) {
        return -(PI - r)
      } else if(dx < 0 && dy > 0) {
        return PI - r
      } else if(dx > 0 && dy < 0) {
        return -r
      } else {
        return r
      }
    }

  val degree: Double
    get() = radiant * 180 / PI

  val unit: Vector2D
    get() = this / magnitude

  val normal: Vector2D
    get() = Vector2D(dy, -dx).unit

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(scalar * dx, scalar * dy)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx / scalar, dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return dx * v.dx + dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(dx + p.x, dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    val v = vectorProject(target)
    val u = target.unit
    val res = v.dx / u.dx
    if(res.isNaN()){
      return v.dy / u.dy
    }
    return res
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (target * (this * target)) / (target.magnitude * target.magnitude)
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}
