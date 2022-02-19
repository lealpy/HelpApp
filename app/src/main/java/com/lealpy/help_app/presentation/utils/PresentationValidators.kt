package com.lealpy.help_app.presentation.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.*

class PresentationValidators {

    fun isValidEmail(email: String) : Boolean {
        return !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && email.length >= EMAIL_MIN_LENGTH
    }

    fun isValidPassword(password : String) : Boolean {
        return password.matches(Regex(PASSWORD_REGEX))
    }

    fun isValidFieldOfActivity(fieldOfActivity : String ) : Boolean {
        return fieldOfActivity.matches(Regex(FIELD_OF_ACTIVITY_REGEX))
    }

    fun isValidName(name : String) : Boolean {
        return name.matches(Regex(NAME_REGEX))
    }

    fun getCapitalizedString(name : String) : String {
        return name.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }

    companion object {
        private const val PASSWORD_MIN_LENGTH = 6
        private const val PASSWORD_MAX_LENGTH = 20
        private const val PASSWORD_REGEX = "[0-9a-zA-Z]{$PASSWORD_MIN_LENGTH,$PASSWORD_MAX_LENGTH}"

        private const val EMAIL_MIN_LENGTH = 6

        private const val NAME_MIN_LENGTH = 2
        private const val NAME_MAX_LENGTH = 20
        private const val NAME_REGEX = "[Ёёа-яА-Яa-zA-Z]{$NAME_MIN_LENGTH,$NAME_MAX_LENGTH}"

        private const val FIELD_OF_ACTIVITY_MIN_LENGTH = 2
        private const val FIELD_OF_ACTIVITY_MAX_LENGTH = 100
        private const val FIELD_OF_ACTIVITY_REGEX = "[0-9Ёёа-яА-Яa-zA-Z]{$FIELD_OF_ACTIVITY_MIN_LENGTH,$FIELD_OF_ACTIVITY_MAX_LENGTH}"
    }

}