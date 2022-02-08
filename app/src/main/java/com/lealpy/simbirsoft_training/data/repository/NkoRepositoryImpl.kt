package com.lealpy.simbirsoft_training.data.repository

import android.util.Log
import com.lealpy.simbirsoft_training.data.api.NkoApi
import com.lealpy.simbirsoft_training.data.database.search_by_nko.NkoDao
import com.lealpy.simbirsoft_training.data.utils.*
import com.lealpy.simbirsoft_training.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.domain.model.NkoItem
import com.lealpy.simbirsoft_training.domain.repository.NkoRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NkoRepositoryImpl @Inject constructor(
    private val nkoDao: NkoDao,
    private val nkoApi: NkoApi,
    private val utils : DataUtils
) : NkoRepository {

    override fun insertToDbNkoItemsFromServer() : Completable {
        return Completable.create{ emitter ->
            nkoApi.getNkoItems()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { nkoItemsFromServer ->
                        insertToDbNkoItems(nkoItemsFromServer)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                {
                                    emitter.onComplete()
                                },
                                { error ->
                                    Log.e(LOG_TAG, error.message.toString())
                                }
                            )
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        emitter.onError(error)
                    }
                )
        }
    }

    override fun insertToDbNkoItemsFromFile() : Completable {
        return Completable.create { emitter ->
            val nkoItemsFromFile = utils.getItemsFromFile<List<NkoItem>>(NKO_ITEMS_FILE_NAME)
            insertToDbNkoItems(nkoItemsFromFile)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        }
    }

    override fun getFromDbNkoItemsByTitle(searchQuery : String) : Single<List<NkoItem>> {
        return nkoDao.getNkoEntitiesByTitle(searchQuery).map { nkoEntities ->
            nkoEntities.toNkoItems()
        }
    }

    override fun getFromDbAllNkoItems() : Single<List<NkoItem>> {
        return nkoDao.getAllNkoEntities().map { nkoEntities ->
            nkoEntities.toNkoItems()
        }
    }

    override fun insertToDbNkoItems(nkoItems : List<NkoItem>) : Completable {
        val nkoEntities = nkoItems.toNkoEntities()
        return nkoDao.insertNkoEntities(nkoEntities)
    }

    companion object {
        private const val NKO_ITEMS_FILE_NAME = "nko_items.json"
    }

}
