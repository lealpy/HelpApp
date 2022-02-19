package com.lealpy.help_app.domain.repository

import com.lealpy.help_app.domain.model.HelpItem
import io.reactivex.rxjava3.core.Single

interface HelpRepository {
    fun getHelpItems() : Single<List<HelpItem>>
}