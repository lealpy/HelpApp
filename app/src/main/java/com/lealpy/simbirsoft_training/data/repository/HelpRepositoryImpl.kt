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
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HelpRepositoryImpl @Inject constructor(
    private val helpDao: HelpDao,
    private val helpApi: HelpApi,
    private val utils: DataUtils
) : HelpRepository {

    override fun insertToDbHelpItemsFromServer() : Completable {
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
                        Log.e(LOG_TAG, error.message.toString())
                        emitter.onError(error)
                    }
                )
        }
    }

    override fun insertToDbHelpItemsFromFile() : Completable {
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

    override fun getFromDbAllHelpItems() : Single<List<HelpItem>> {
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
