package com.kenkoro.practice.landscapeDrawable

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import java.util.Random

class Land : LandscapeItem {
  private var _color0 = 0
  var color0: Int
    get() = _color0
    set(value) {
      _color0 = value
      shader = null
    }

  private var _color1 = 0
  var color1: Int
    get() = _color1
    set(value) {
      _color1 = value
      shader = null
    }

  private var _fluctuation = 0.0f
  var fluctuation: Float
    get() = _fluctuation
    set(value) {
      _fluctuation = value
      path.reset()
      trees.clear()
      shader = null
    }

  private var _treeHeight = 0.0f
  var treeHeight: Float
    get() = _treeHeight
    set(value) {
      _treeHeight = value
      path.reset()
      trees.clear()
      shader = null
    }

  private var _treeRandom = 0.0f
  var treeRandom: Float
    get() = _treeRandom
    set(value) {
      _treeRandom = value
      path.reset()
      trees.clear()
    }

  private val random: Random
  private val path = Path()
  private val trees = ArrayList<Tree>()
  private var shader: LinearGradient? = null
  var wind: Float = 0F

  constructor(
    color0: Int,
    color1: Int,
    fluctuation: Float,
    treeHeight: Float,
    treeRandom: Float,
    random: Random,
    paint: Paint
  ) : super(paint) {
    this.color0 = color0
    this.color1 = color1
    this.fluctuation = fluctuation
    this.treeHeight = treeHeight
    this.treeRandom = treeRandom
    this.random = random
  }

  constructor(
    color0: Int,
    color1: Int,
    fluctuation: Float,
    treeHeight: Float = fluctuation,
    treeRandom: Float = 0.8f
  ) : this(
    color0,
    color1,
    fluctuation,
    treeHeight,
    treeRandom,
    Random(),
    Paint(Paint.ANTI_ALIAS_FLAG)
  )

  override fun onSizeChanged() {
    path.reset()
    trees.clear()
    shader = null
  }

  fun initLandAndPlaceTrees() {
    if (path.isEmpty) {
      path.moveTo(0f, height)
      path.lineTo(width, height)
      var prevX: Float = width
      var prevY = (treeHeight + Math.random() * fluctuation).toFloat()
      path.lineTo(prevX, prevY)

      val segments = (width / treeHeight / 3).toInt()
      val treeWidth = treeHeight * 2 / 3f
      for (i in 0..segments) {
        val x: Float = (width * (segments - i) / segments)
        val y = (treeHeight + Math.random() * fluctuation).toFloat()
        val x33 = MathUtils.lerp(prevX, x, 0.33f)
        val x67 = MathUtils.lerp(prevX, x, 0.67f)
        path.cubicTo(x33, prevY, x67, y, x, y)
        if (random.nextFloat() > treeRandom) {
          val tree = Tree(wind, paint)
          tree.x = prevX - treeWidth / 2
          tree.y = prevY - treeHeight
          tree.setSize(treeWidth, treeHeight)
          trees.add(tree)
        }
        if (random.nextFloat() > treeRandom) {
          val y33 = MathUtils.lerp(prevY, y, 0.33f)
          val tree = Tree(wind, paint)
          tree.x = x33 - treeWidth / 2
          tree.y = y33 - treeHeight
          tree.setSize(treeWidth, treeHeight)
          trees.add(tree)
        }
        if (random.nextFloat() > treeRandom) {
          val y67 = MathUtils.lerp(prevY, y, 0.67f)
          val tree = Tree(wind, paint)
          tree.x = x67 - treeWidth / 2
          tree.y = y67 - treeHeight
          tree.setSize(treeWidth, treeHeight)
          trees.add(tree)
        }
        if (random.nextFloat() > treeRandom) {
          val tree = Tree(wind, paint)
          tree.x = x - treeWidth / 2
          tree.y = y - treeHeight
          tree.setSize(treeWidth, treeHeight)
          trees.add(tree)
        }
        prevX = x
        prevY = y
      }
      path.close()
    }

    if (shader == null) {
      shader = LinearGradient(0F, height, 0F, treeHeight, color0, color1, Shader.TileMode.CLAMP)
    }
  }

  override fun onDraw(canvas: Canvas) {
    initLandAndPlaceTrees()

    if (wind > 0) {
      for (tree in trees) {
        tree.wind = wind
      }
    }

    paint.color = color1
    paint.shader = shader
    for (tree in trees) {
      tree.draw(canvas)
    }
    canvas.drawPath(path, paint)
    paint.shader = null
  }
}