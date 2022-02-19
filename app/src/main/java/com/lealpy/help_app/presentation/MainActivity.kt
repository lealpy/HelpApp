package com.lealpy.help_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lealpy.help_app.R
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
            bottomNavView.visibility = when(destination.id) {
                R.id.navigationNews -> View.VISIBLE
                R.id.navigationSearch -> View.VISIBLE
                R.id.navigationHelp -> View.VISIBLE
                R.id.navigationHistory -> View.VISIBLE
                R.id.navigationProfile -> View.VISIBLE
                else -> View.GONE
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
