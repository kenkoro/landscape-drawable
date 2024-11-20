package com.kenkoro.practice.landscapeDrawable

import java.util.Random
import kotlin.math.hypot

object MathUtils {
  private val random = Random()

  fun constrain(amount: Float, low: Float, high: Float): Float {
    return if (amount < low) {
      low
    } else {
      if (amount > high) high else amount
    }
  }

  fun dist(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val x = x2 - x1
    val y = y2 - y1
    return hypot(x, y)
  }

  fun lerp(start: Float, stop: Float, amount: Float): Float {
    return start + (stop - start) * amount
  }

  fun randomForce(maxValue: Float): Float {
    return random.nextFloat() * maxValue * 2 - maxValue
  }
}