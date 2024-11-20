package com.kenkoro.practice.landscapeDrawable

import android.os.Bundle
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

    val sky = Sky(
      cloudSize = 300F,
      cloudColor = R.color.white,
      puffCount = 5,
      wind = 1F,
    )
    val drawableSky = ItemDrawable(sky)
    ivLandscape = binding.ivLandscape.apply {
      setImageDrawable(drawableSky)
    }
  }
}