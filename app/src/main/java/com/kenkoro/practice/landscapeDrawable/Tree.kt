package com.kenkoro.practice.landscapeDrawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Tree : LandscapeItem {
  private val path = Path()
  private var _wind = 0F
  var wind: Float
    get() = _wind
    set(value) {
      if (_wind != value) {
        path.reset()
      }
      _wind = value
    }

  constructor(wind: Float = 0F, paint: Paint) : super(paint) {
    this.wind = wind
  }

  constructor(wind: Float = 0F) : this(wind, Paint(Paint.ANTI_ALIAS_FLAG))

  override fun onSizeChanged() {
    path.reset()
  }

  private fun initPath() {
    if (path.isEmpty) {
      val cx = width / 2f
      path.addRect(cx - width / 10, height * 5 / 6, cx + width / 10, height * 2, Path.Direction.CCW)
      path.moveTo(0f, height * 5 / 6)
      path.quadTo(cx - width / 6, height * 5 / 12, cx + wind, 0f)
      path.quadTo(cx + width / 6, height * 5 / 12, width, height * 5 / 6)
      path.close()
    }
  }

  override fun onDraw(canvas: Canvas) {
    initPath()
    canvas.drawPath(path, paint)
  }
}