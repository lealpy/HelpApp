package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository {
    fun insertToDbNewsItemsFromServer() : Completable
    fun insertToDbNewsItemsFromFile() : Completable
    fun insertToDbNewsItems(newsItems : List<NewsItem>) : Completable
    fun getFromDbAllNewsPreviewItems() : Single<List<NewsPreviewItem>>
    fun getFromDbNewsDescriptionItem(id : Long) : Single<NewsDescriptionItem>
    fun getFromDbAllNewsItems() : Single<List<NewsItem>>
    fun funInsertToDbWatchedNewsId(id : Long) : Completable
    fun getFromDbUnwatchedNewsIdList() : Single<Int>
}
