package com.lealpy.help_app.data.repository

import android.util.Log
import com.lealpy.help_app.data.api.NewsApi
import com.lealpy.help_app.data.database.news.NewsDao
import com.lealpy.help_app.data.database.news.WatchedNewsEntity
import com.lealpy.help_app.data.utils.*
import com.lealpy.help_app.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.model.NewsItem
import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao : NewsDao,
    private val newsApi: NewsApi,
    private val utils : DataUtils
) : NewsRepository {

    override fun getAllNewsPreviewItems() : Single<List<NewsPreviewItem>> {
        return getNewsItems().map { newsItems ->
            newsItems.toNewsPreviewItems()
        }
    }

    override fun getNewsDescriptionItem(id : Long) : Single<NewsDescriptionItem> {
        return newsDao.getNewsDescriptionEntities(id).map { newsDescriptionEntity ->
            newsDescriptionEntity.toNewsDescriptionItem()
        }
    }

    override fun funInsertToDbWatchedNewsId(id : Long): Completable {
        val watchedNewsEntity = WatchedNewsEntity(id)
        return newsDao.insertWatchedNewsEntity(watchedNewsEntity)
    }

    override fun getUnwatchedNewsNumber() : Single<Int> {
        return newsDao.getUnwatchedNewsIdList().map { idList ->
            idList.size
        }
    }

    private fun getNewsItems() : Single<List<NewsItem>> {
        return newsApi.getNewsItems()
            .doOnSuccess { newsItemsFromServer ->
                insertToDbNewsItems(newsItemsFromServer).blockingSubscribe()
            }
            .onErrorResumeNext { error ->
                Log.e(LOG_TAG, error.message.toString())
                getFromDbAllNewsItems()
                    .flatMap { newsItemsFromDb ->
                        if(newsItemsFromDb.isNotEmpty()) {
                            Single.just(newsItemsFromDb)
                        } else {
                            val newsItemsFromFile = utils.getItemsFromFile<List<NewsItem>>(NEWS_ITEMS_FILE_NAME)
                            insertToDbNewsItems(newsItemsFromFile).blockingSubscribe()
                            Single.just(newsItemsFromFile)
                        }
                    }
            }
    }

    private fun insertToDbNewsItems(newsItems : List<NewsItem>) : Completable {
        val newsEntities = newsItems.toNewsEntities()
        return newsDao.insertNewsEntities(newsEntities)
    }

    private fun getFromDbAllNewsItems() : Single<List<NewsItem>> {
        return newsDao.getAllNewsEntities().map { newsEntities ->
            newsEntities.toNewsItems()
        }
    }

    companion object {
        private const val NEWS_ITEMS_FILE_NAME = "news_items.json"
    }

}
