package com.lealpy.simbirsoft_training.data.repository

import android.util.Log
import com.lealpy.simbirsoft_training.data.api.HelpApi
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.utils.DataUtils
import com.lealpy.simbirsoft_training.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.data.utils.toHelpEntities
import com.lealpy.simbirsoft_training.data.utils.toHelpItems
import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.domain.repository.HelpRepository
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HelpRepositoryImpl @Inject constructor(
    private val helpDao: HelpDao,
    private val helpApi: HelpApi,
    private val utils: DataUtils
) : HelpRepository {

    override fun saveToDbHelpItemsFromServer() : Completable {
        return Completable.create{ emitter ->
            helpApi.getHelpItems()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { helpItemsFromServer ->
                        insertToDbHelpItems(helpItemsFromServer)
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
                        error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
                        emitter.onError(error)
                    }
                )
        }
    }

    override fun saveToDbHelpItemsFromFile() : Completable {
        return Completable.create{ emitter ->
            val helpItemsFromFile = utils.getItemsFromFile<List<HelpItem>>(HELP_ITEMS_FILE_NAME)
            insertToDbHelpItems(helpItemsFromFile)
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

    override fun getFromDbHelpItems() : Single<List<HelpItem>> {
        return helpDao.getAllHelpEntities().map { helpEntities ->
            helpEntities.toHelpItems()
        }
    }

    override fun insertToDbHelpItems(helpItems: List<HelpItem>) : Completable {
        val helpEntities = helpItems.toHelpEntities()
        return helpDao.insertHelpEntities(helpEntities)
    }

    companion object {
        private const val HELP_ITEMS_FILE_NAME = "help_items.json"
    }
}
