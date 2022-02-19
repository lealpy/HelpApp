package com.lealpy.help_app.data.repository

import android.util.Log
import com.lealpy.help_app.data.api.NkoApi
import com.lealpy.help_app.data.database.search_by_nko.NkoDao
import com.lealpy.help_app.data.utils.*
import com.lealpy.help_app.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.help_app.domain.model.NkoItem
import com.lealpy.help_app.domain.repository.NkoRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NkoRepositoryImpl @Inject constructor(
    private val nkoDao: NkoDao,
    private val nkoApi: NkoApi,
    private val utils : DataUtils
) : NkoRepository {

    override fun getFromDbNkoItemsByTitle(searchQuery : String) : Single<List<NkoItem>> {
        return nkoDao.getNkoEntitiesByTitle(searchQuery).map { nkoEntities ->
            nkoEntities.toNkoItems()
        }
    }

    override fun updateNkoItems() : Completable {
        return nkoApi.getNkoItems()
            .doOnSuccess { nkoItems ->
                insertToDbNkoItems(nkoItems).blockingSubscribe()
            }
            .onErrorResumeNext{ error ->
                Log.e(LOG_TAG, error.message.toString())
                getFromDbAllNkoItems()
                    .flatMap { nkoItemsFromDb ->
                        if(nkoItemsFromDb.isNotEmpty()) {
                            Single.just(nkoItemsFromDb)
                        } else {
                            val nkoItemsFromFile = utils.getItemsFromFile<List<NkoItem>>(NKO_ITEMS_FILE_NAME)
                            insertToDbNkoItems(nkoItemsFromFile).blockingSubscribe()
                            Single.just(nkoItemsFromFile)
                        }
                    }
            }
            .flatMapCompletable {
                Completable.complete()
            }
    }

    private fun getFromDbAllNkoItems() : Single<List<NkoItem>> {
        return nkoDao.getAllNkoEntities().map { nkoEntities ->
            nkoEntities.toNkoItems()
        }
    }

    private fun insertToDbNkoItems(nkoItems : List<NkoItem>) : Completable {
        val nkoEntities = nkoItems.toNkoEntities()
        return nkoDao.insertNkoEntities(nkoEntities)
    }

    companion object {
        private const val NKO_ITEMS_FILE_NAME = "nko_items.json"
    }

}
