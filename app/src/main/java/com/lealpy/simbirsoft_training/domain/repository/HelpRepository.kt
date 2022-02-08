package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.HelpItem
import io.reactivex.Completable
import io.reactivex.Single

interface HelpRepository {
    fun insertToDbHelpItemsFromServer() : Completable
    fun insertToDbHelpItemsFromFile() : Completable
    fun getFromDbAllHelpItems() : Single<List<HelpItem>>
    fun insertToDbHelpItems(helpItems: List<HelpItem>) : Completable
}