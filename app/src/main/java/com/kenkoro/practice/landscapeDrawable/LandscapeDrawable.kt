package com.kenkoro.practice.landscapeDrawable

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class LandscapeDrawable : Drawable() {
  private val paint = Paint().apply {
    color = Color.RED
  }

  override fun draw(canvas: Canvas) {
  }

  override fun setAlpha(aplha: Int) {
  }

  override fun setColorFilter(colorFilter: ColorFilter?) {
  }

  override fun getOpacity(): Int = PixelFormat.OPAQUE
}