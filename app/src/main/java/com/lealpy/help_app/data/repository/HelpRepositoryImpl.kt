package com.lealpy.help_app.data.repository

import android.util.Log
import com.lealpy.help_app.data.api.HelpApi
import com.lealpy.help_app.data.database.help.HelpDao
import com.lealpy.help_app.data.utils.DataUtils
import com.lealpy.help_app.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.help_app.data.utils.toHelpEntities
import com.lealpy.help_app.data.utils.toHelpItems
import com.lealpy.help_app.domain.model.HelpItem
import com.lealpy.help_app.domain.repository.HelpRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HelpRepositoryImpl @Inject constructor(
    private val helpDao: HelpDao,
    private val helpApi: HelpApi,
    private val utils: DataUtils,
) : HelpRepository {

    override fun getHelpItems(): Single<List<HelpItem>> {
        return helpApi.getHelpItems()
            .doOnSuccess { helpItemsFromServer ->
                insertToDbHelpItems(helpItemsFromServer).blockingSubscribe()
            }
            .onErrorResumeNext { error ->
                Log.e(LOG_TAG, error.message.toString())
                getFromDbAllHelpItems()
                    .flatMap { helpItemsFromDb ->
                        if (helpItemsFromDb.isNotEmpty()) {
                            Single.just(helpItemsFromDb)
                        } else {
                            val helpItemsFromFile =
                                utils.getItemsFromFile<List<HelpItem>>(HELP_ITEMS_FILE_NAME)
                            insertToDbHelpItems(helpItemsFromFile).blockingSubscribe()
                            Single.just(helpItemsFromFile)
                        }
                    }
            }
    }

    private fun getFromDbAllHelpItems(): Single<List<HelpItem>> {
        return helpDao.getAllHelpEntities().map { helpEntities ->
            helpEntities.toHelpItems()
        }
    }

    private fun insertToDbHelpItems(helpItems: List<HelpItem>): Completable {
        val helpEntities = helpItems.toHelpEntities()
        return helpDao.insertHelpEntities(helpEntities)
    }

    companion object {
        private const val HELP_ITEMS_FILE_NAME = "help_items.json"
    }

}
