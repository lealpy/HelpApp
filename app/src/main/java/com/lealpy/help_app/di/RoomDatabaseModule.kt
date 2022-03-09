package com.lealpy.help_app.di

import android.content.Context
import androidx.room.Room
import com.lealpy.help_app.data.database.AppDatabase
import com.lealpy.help_app.data.database.help.HelpDao
import com.lealpy.help_app.data.database.news.NewsDao
import com.lealpy.help_app.data.database.search_by_events.EventDao
import com.lealpy.help_app.data.database.search_by_nko.NkoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            ROOM_DATABASE_FILE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHelpDao(appDatabase: AppDatabase): HelpDao {
        return appDatabase.helpDao()
    }

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase): EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideNkoDao(appDatabase: AppDatabase): NkoDao {
        return appDatabase.nkoDao()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }

    companion object {
        private const val ROOM_DATABASE_FILE_NAME = "database.db"
    }

}
