package com.lealpy.simbirsoft_training.di

import android.content.Context
import androidx.room.Room
import com.lealpy.simbirsoft_training.App
import com.lealpy.simbirsoft_training.data.database.AppDatabase
import com.lealpy.simbirsoft_training.data.database.events.EventDao
import com.lealpy.simbirsoft_training.data.database.events.EventRepository
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.database.help.HelpRepository
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext : Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            ROOM_DATABASE_FILE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideResourceManager(
        @ApplicationContext context: Context
    ): ResourceManager = ResourceManager(context.resources)

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase) : EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideHelpDao(appDatabase: AppDatabase) : HelpDao {
        return appDatabase.helpDao()
    }

    @Provides
    @Singleton
    fun provideHelpRepository(helpDao : HelpDao) : HelpRepository {
        return HelpRepository(helpDao)
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventDao: EventDao) : EventRepository {
        return EventRepository(eventDao)
    }

    companion object {
        private const val RETROFIT_BASE_URL = "https://help-app.getsandbox.com"
        private const val ROOM_DATABASE_FILE_NAME = "database.db"
    }

}
