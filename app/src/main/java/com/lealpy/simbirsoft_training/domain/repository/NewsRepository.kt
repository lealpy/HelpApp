package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository {
    fun saveToDbNewsItemsFromServer() : Completable
    fun saveToDbNewsItemsFromFile() : Completable
    fun insertToDbNewsItems(newsItems : List<NewsItem>) : Completable
    fun getFromDbAllNewsPreviewItems() : Single<List<NewsPreviewItem>>
    fun getFromDbNewsDescriptionItem(id : Long) : Single<NewsDescriptionItem>
    fun getFromDbAllNewsItems() : Single<List<NewsItem>>
    fun funInsertWatchedNewsId(id : Long) : Completable
    fun getFromDbUnwatchedNewsId() : Single<Int>
}
