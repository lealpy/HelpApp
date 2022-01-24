package com.lealpy.simbirsoft_training.presentation.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lealpy.simbirsoft_training.presentation.MainActivity
import com.lealpy.simbirsoft_training.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel : SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initObservers()
        viewModel.onActivityCreated()
    }

    private fun initObservers() {
        viewModel.startBadgeNumber.observe(this) {startBadgeNumber ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_KEY, startBadgeNumber)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_KEY = "SPLASH_SCREEN_ACTIVITY_EXTRA_KEY"
    }
}
