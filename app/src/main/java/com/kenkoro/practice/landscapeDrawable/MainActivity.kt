package com.kenkoro.practice.landscapeDrawable

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.kenkoro.practice.landscapeDrawable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var ivLandscape: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val landscapeDrawable = LandscapeDrawable(
      starCount = 50,
      starSize = 5F,
      starColor = Color.parseColor("#FFCDA5"),
      cloudCount = 10,
      cloudSize = 500F,
      cloudColor = Color.parseColor("#C7C4BF"),
      windStrength = 20F,
      maxWind = 30F,
      sunSize = 70F,
      sunColor = Color.parseColor("#F5F5DC"),
      skyColor = Color.parseColor("#2E4482"),
      skyHeight = 800F,
      fogColor = Color.parseColor("#ABAEB0"),
      landscapeColor = Color.parseColor("#5E6885"),
      planesCount = 5,
      landscapeHeight = 1300F,
      treeHeight = 250F,
    )
    ivLandscape = binding.ivLandscape
    ivLandscape.setImageDrawable(landscapeDrawable)

    Handler(mainLooper).postDelayed({
      ivLandscape.invalidateDrawable(landscapeDrawable)
    }, 1L)
  }
}