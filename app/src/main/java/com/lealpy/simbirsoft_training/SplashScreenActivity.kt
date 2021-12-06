package com.lealpy.simbirsoft_training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intent = Intent(this, MainActivity::class.java)

        GlobalScope.launch {
            delay(1000L)
            println("World!")
            startActivity(intent)
            finish()
        }

    }
}
