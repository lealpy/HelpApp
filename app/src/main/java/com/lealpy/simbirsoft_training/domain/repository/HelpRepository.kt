package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.HelpItem
import io.reactivex.Completable
import io.reactivex.Single

interface HelpRepository {
    fun saveToDbHelpItemsFromServer() : Completable
    fun saveToDbHelpItemsFromFile() : Completable
    fun getFromDbHelpItems() : Single<List<HelpItem>>
    fun insertToDbHelpItems(helpItems: List<HelpItem>) : Completable
}