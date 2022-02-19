package com.lealpy.help_app.presentation.model

import android.graphics.Bitmap

data class UserUi (
    val id : String,
    val name : String,
    val surname : String,
    val dateOfBirth : String,
    val fieldOfActivity : String,
    val email : String,
    val avatar : Bitmap?
)
