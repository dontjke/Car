package com.stepanov.car

import android.graphics.Path
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.stepanov.car.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var toRightAnimation = false
    private val rotationStart = -25f
    private val rotationBack = 145f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.carImageView.animate().rotation(rotationStart)
        binding.goButton.setOnClickListener {
            val changeBounds = ChangeBounds()
            changeBounds.setPathMotion(ArcMotion())
            changeBounds.duration = 2000
            TransitionManager.beginDelayedTransition(
                binding.transitionsContainer,
                changeBounds
            )
            toRightAnimation =! toRightAnimation
            val params = binding.carImageView.layoutParams as FrameLayout.LayoutParams
            params.gravity =
                if (toRightAnimation) {
                    binding.carImageView.animate().rotation(rotationStart)
                    Gravity.END or Gravity.BOTTOM
                } else {
                    binding.carImageView.animate().rotation(rotationBack)
                    Gravity.START or Gravity.TOP
                }
            binding.carImageView.layoutParams = params
        }
    }
}