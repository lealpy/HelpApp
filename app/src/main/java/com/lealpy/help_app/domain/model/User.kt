package com.lealpy.help_app.domain.model

data class User (
    var id : String = "",
    var name : String = "",
    var surname : String = "",
    var dateOfBirth : Long = 0,
    var fieldOfActivity : String = "",
    var email : String = "",
    var avatarUrl : String = ""
)
