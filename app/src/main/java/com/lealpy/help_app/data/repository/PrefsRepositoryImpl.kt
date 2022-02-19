package com.lealpy.help_app.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.lealpy.help_app.domain.repository.PrefsRepository
import javax.inject.Inject

class PrefsRepositoryImpl @Inject constructor(
    private val prefs : SharedPreferences
) : PrefsRepository {

    override fun setSettingGetPushToPrefs(settingGetPush : Boolean) {
        prefs.edit{
            putBoolean(SETTING_GET_PUSH_KEY, settingGetPush)
        }
    }

    override fun getSettingGetPushToPrefs() : Boolean {
        return prefs.getBoolean(SETTING_GET_PUSH_KEY, SETTING_GET_PUSH_DEFAULT_VALUE)
    }

    companion object {
        const val SETTING_GET_PUSH_KEY = "SETTING_GET_PUSH_KEY"
        const val SETTING_GET_PUSH_DEFAULT_VALUE = true
    }

}
