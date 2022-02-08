package com.lealpy.simbirsoft_training.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lealpy.simbirsoft_training.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    val badgeSubject: PublishSubject<Int> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavView()
    }

    private fun initBottomNavView() {
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

        badgeSubject.subscribe(
            { badgeNumber ->
                bottomNavView.getOrCreateBadge(
                    R.id.navigationNews
                ).apply {
                    number = badgeNumber
                    isVisible = number != 0
                }
            },
            { error ->
                throw Exception(error.message)
            }
        )
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
            navController.currentDestination?.id == R.id.navigationProfile
        ) {
            finish()
        }
        super.onBackPressed()
    }

}
