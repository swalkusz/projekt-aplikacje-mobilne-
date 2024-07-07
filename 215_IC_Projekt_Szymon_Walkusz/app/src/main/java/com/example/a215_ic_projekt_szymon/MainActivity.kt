package com.example.a215_ic_projekt_szymon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.a215_ic_projekt_szymon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val ANIMATION_DURATION = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load and start the animation
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        binding.textViewAMW.startAnimation(rotateAnimation)

        // Use Handler to switch activity after animation duration
        Handler().postDelayed({
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }, ANIMATION_DURATION)
    }
}

