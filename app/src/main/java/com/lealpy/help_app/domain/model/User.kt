package com.lealpy.help_app.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val dateOfBirth: Long = 0,
    val fieldOfActivity: String = "",
    val email: String = "",
    val avatarUrl: String = "",
)
