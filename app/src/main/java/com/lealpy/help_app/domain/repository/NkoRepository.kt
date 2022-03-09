package com.lealpy.help_app.domain.repository

import com.lealpy.help_app.domain.model.NkoItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NkoRepository {
    fun getFromDbNkoItemsByTitle(searchQuery: String): Single<List<NkoItem>>
    fun updateNkoItems(): Completable
}