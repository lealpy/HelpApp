package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lealpy.simbirsoft_training.training.publication.PublicationProgram
import com.lealpy.simbirsoft_training.training.user.UserProgram

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PublicationProgram.execute()
        //UserProgram.execute()

        val navController = findNavController(R.id.navHostFragment)
        val navView: BottomNavigationView = findViewById(R.id.navView)
        navView.setupWithNavController(navController)
    }
}
