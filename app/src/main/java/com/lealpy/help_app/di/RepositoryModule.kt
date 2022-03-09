package com.lealpy.help_app.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.lealpy.help_app.data.api.EventApi
import com.lealpy.help_app.data.api.HelpApi
import com.lealpy.help_app.data.api.NewsApi
import com.lealpy.help_app.data.api.NkoApi
import com.lealpy.help_app.data.database.help.HelpDao
import com.lealpy.help_app.data.database.news.NewsDao
import com.lealpy.help_app.data.database.search_by_events.EventDao
import com.lealpy.help_app.data.database.search_by_nko.NkoDao
import com.lealpy.help_app.data.repository.*
import com.lealpy.help_app.data.utils.DataUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHelpRepository(
        helpDao: HelpDao,
        helpApi: HelpApi,
        dataUtils: DataUtils,
    ): HelpRepositoryImpl {
        return HelpRepositoryImpl(helpDao, helpApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideEventRepository(
        eventDao: EventDao,
        eventApi: EventApi,
        dataUtils: DataUtils,
    ): EventRepositoryImpl {
        return EventRepositoryImpl(eventDao, eventApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideNkoRepository(
        nkoDao: NkoDao,
        nkoApi: NkoApi,
        dataUtils: DataUtils,
    ): NkoRepositoryImpl {
        return NkoRepositoryImpl(nkoDao, nkoApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDao: NewsDao,
        newsApi: NewsApi,
        dataUtils: DataUtils,
    ): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsDao, newsApi, dataUtils)
    }

    @Provides
    @Singleton
    fun providePrefsRepository(
        prefs: SharedPreferences,
    ): PrefsRepositoryImpl {
        return PrefsRepositoryImpl(prefs)
    }

    @Provides
    @Singleton
    fun provideWorkerRepository(
        @ApplicationContext appContext: Context,
    ): WorkerRepositoryImpl {
        return WorkerRepositoryImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideFireRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDatabase: FirebaseDatabase,
        firebaseStorage: FirebaseStorage,
    ): FireRepositoryImpl {
        return FireRepositoryImpl(firebaseAuth, firebaseDatabase, firebaseStorage)
    }

}
