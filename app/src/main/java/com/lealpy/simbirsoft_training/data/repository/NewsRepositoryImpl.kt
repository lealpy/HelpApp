package com.lealpy.simbirsoft_training.data.repository

import android.util.Log
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.data.database.news.NewsDao
import com.lealpy.simbirsoft_training.data.database.news.WatchedNewsEntity
import com.lealpy.simbirsoft_training.data.utils.*
import com.lealpy.simbirsoft_training.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem
import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao : NewsDao,
    private val newsApi: NewsApi,
    private val utils : DataUtils
) : NewsRepository {

    override fun insertToDbNewsItemsFromServer() : Completable {
        return Completable.create { emitter ->
            newsApi.getNewsItems()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { newsItemsFromServer ->
                        insertToDbNewsItems(newsItemsFromServer)
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

    override fun insertToDbNewsItemsFromFile() : Completable {
        return Completable.create{ emitter ->
            val newsItemsFromFile = utils.getItemsFromFile<List<NewsItem>>(NEWS_ITEMS_FILE_NAME)
            insertToDbNewsItems(newsItemsFromFile)
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

    override fun insertToDbNewsItems(newsItems : List<NewsItem>) : Completable {
        val newsEntities = newsItems.toNewsEntities()
        return newsDao.insertNewsEntities(newsEntities)
    }

    override fun getFromDbAllNewsPreviewItems() : Single<List<NewsPreviewItem>> {
        return newsDao.getNewsPreviewEntities().map { newsPreviewEntities ->
            newsPreviewEntities.toNewsPreviewItems()
        }
    }

    override fun getFromDbNewsDescriptionItem(id : Long) : Single<NewsDescriptionItem> {
        return newsDao.getNewsDescriptionEntities(id).map { newsDescriptionEntity ->
            newsDescriptionEntity.toNewsDescriptionItem()
        }
    }

    override fun getFromDbAllNewsItems() : Single<List<NewsItem>> {
        return newsDao.getAllNewsEntities().map { newsEntities ->
            newsEntities.toNewsItems()
        }
    }

    override fun funInsertToDbWatchedNewsId(id : Long): Completable {
        val watchedNewsEntity = WatchedNewsEntity(id)
        return newsDao.insertWatchedNewsEntity(watchedNewsEntity)
    }

    override fun getFromDbUnwatchedNewsIdList() : Single<Int> {
        return newsDao.getUnwatchedNewsIdList().map { idList ->
            idList.size
        }
    }

    companion object {
        private const val NEWS_ITEMS_FILE_NAME = "news_items.json"
    }

}
