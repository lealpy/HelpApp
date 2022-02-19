package com.lealpy.help_app.domain.use_cases.prefs

import com.lealpy.help_app.domain.repository.PrefsRepository
import javax.inject.Inject

class SetSettingGetPushUseCase @Inject constructor(
    private val repository : PrefsRepository
) {

    operator fun invoke(settingGetPush : Boolean) {
        repository.setSettingGetPushToPrefs(settingGetPush)
    }

}
