package com.lealpy.help_app.domain.repository

interface PrefsRepository {
    fun setSettingGetPushToPrefs(settingGetPush : Boolean)
    fun getSettingGetPushToPrefs() : Boolean
}