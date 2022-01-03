package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navView)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (
            navController.currentDestination?.id == R.id.navigationNews ||
            navController.currentDestination?.id == R.id.navigationSearch ||
            navController.currentDestination?.id == R.id.navigationHelp ||
            navController.currentDestination?.id == R.id.navigationHistory ||
            navController.currentDestination?.id == R.id.navigationProfile
        ) {
            finish()
        }
        super.onBackPressed()
    }
}
