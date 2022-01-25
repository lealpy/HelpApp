package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.NkoItem
import io.reactivex.Completable
import io.reactivex.Single

interface NkoRepository {
    fun saveToDbNkoItemsFromServer() : Completable
    fun saveToDbNkoItemsFromFile() : Completable
    fun getFromDbNkoItemsByTitle(searchQuery : String) : Single<List<NkoItem>>
    fun getFromDbAllNkoItems() : Single<List<NkoItem>>
    fun insertToDbNkoItems(nkoItems : List<NkoItem>) : Completable
}