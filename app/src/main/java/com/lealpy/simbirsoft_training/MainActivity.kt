package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigationNews -> showBottomNavView()
                R.id.navigationSearch -> showBottomNavView()
                R.id.navigationHelp -> showBottomNavView()
                R.id.navigationHistory -> showBottomNavView()
                R.id.navigationProfile -> showBottomNavView()
                else -> hideBottomNavView()
            }
        }

    }

    private fun showBottomNavView() {
        bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNavView() {
        bottomNavView.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (
            navController.currentDestination?.id == R.id.navigationNews ||
            navController.currentDestination?.id == R.id.navigationSearch ||
            navController.currentDestination?.id == R.id.navigationHelp ||
            navController.currentDestination?.id == R.id.navigationHistory ||
            navController.currentDestination?.id == R.id.navigationProfile ||
            navController.currentDestination?.id == R.id.authorizationFragment
        ) {
            finish()
        }
        super.onBackPressed()
    }
}
