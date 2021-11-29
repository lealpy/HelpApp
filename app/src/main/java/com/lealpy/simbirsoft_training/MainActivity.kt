package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lealpy.simbirsoft_training.training.publication.PublicationProgram

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PublicationProgram.execute()
    }

}