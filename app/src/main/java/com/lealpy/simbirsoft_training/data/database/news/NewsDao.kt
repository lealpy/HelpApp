package com.lealpy.simbirsoft_training.data.database.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query("SELECT id, image_url, title, abbreviated_text, date, fund_name, address, phone, image_2_url, image_3_url, full_text FROM news_entities WHERE id LIKE :id")
    fun getNewsDescriptionEntities(id : Long) : Single<NewsDescriptionDb>

    @Query("SELECT id, image_url, title, abbreviated_text, date, is_children_category, is_adult_category, is_elderly_category, is_animal_category, is_event_category FROM news_entities")
    fun getNewsPreviewEntities() : Single<List<NewsPreviewDb>>

    @Query("SELECT * FROM news_entities")
    fun getAllNewsEntities() : Single<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsEntities(newsEntities : List<NewsEntity>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchedNewsEntity(watchedNewsEntity : WatchedNewsEntity) : Completable

    @Query("SELECT id FROM news_entities EXCEPT SELECT id FROM watched_news_entities")
    fun getUnwatchedNewsIdList() : Single<List<Long>>

}
