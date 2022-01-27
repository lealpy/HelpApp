package com.lealpy.simbirsoft_training.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.lealpy.simbirsoft_training.data.api.EventApi
import com.lealpy.simbirsoft_training.data.api.HelpApi
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.data.api.NkoApi
import com.lealpy.simbirsoft_training.data.database.AppDatabase
import com.lealpy.simbirsoft_training.data.database.search_by_events.EventDao
import com.lealpy.simbirsoft_training.data.repository.EventRepositoryImpl
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.database.news.NewsDao
import com.lealpy.simbirsoft_training.data.database.search_by_nko.NkoDao
import com.lealpy.simbirsoft_training.data.repository.HelpRepositoryImpl
import com.lealpy.simbirsoft_training.data.repository.NewsRepositoryImpl
import com.lealpy.simbirsoft_training.data.repository.NkoRepositoryImpl
import com.lealpy.simbirsoft_training.data.utils.DataUtils
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_events.InsertToDbEventItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.help.GetFromDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.help.InsertToDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetFromDbAllNewsPreviewItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetFromDbNewsDescriptionItemByIdUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.InsertToDbWatchedNewsIdUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.InsertToDbNewsItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.GetFromDbAllNkoItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.InsertToDbNkoItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /** Repository */

    @Provides
    @Singleton
    fun provideHelpRepository(
        helpDao : HelpDao,
        helpApi: HelpApi,
        dataUtils: DataUtils
    ) : HelpRepositoryImpl {
        return HelpRepositoryImpl(helpDao, helpApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideEventRepository(
        eventDao: EventDao,
        eventApi: EventApi,
        dataUtils: DataUtils
    ) : EventRepositoryImpl {
        return EventRepositoryImpl(eventDao, eventApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideNkoRepository(
        nkoDao: NkoDao,
        nkoApi: NkoApi,
        dataUtils: DataUtils
    ) : NkoRepositoryImpl {
        return NkoRepositoryImpl(nkoDao, nkoApi, dataUtils)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDao: NewsDao,
        newsApi: NewsApi,
        dataUtils: DataUtils
    ) : NewsRepositoryImpl {
        return NewsRepositoryImpl(newsDao, newsApi, dataUtils)
    }

    /** Room */

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
    fun provideHelpDao(appDatabase: AppDatabase) : HelpDao {
        return appDatabase.helpDao()
    }

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase) : EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideNkoDao(appDatabase: AppDatabase) : NkoDao {
        return appDatabase.nkoDao()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) : NewsDao {
        return appDatabase.newsDao()
    }

    /** Retrofit */

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHtpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .client(okHtpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHelpApi(retrofit: Retrofit) : HelpApi {
        return retrofit.create(HelpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit) : NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventApi(retrofit: Retrofit) : EventApi {
        return retrofit.create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNkoApi(retrofit: Retrofit) : NkoApi {
        return retrofit.create(NkoApi::class.java)
    }

    /** Use cases */

    @Provides
    @Singleton
    fun provideGetFromDbHelpItemsUseCase(
        helpRepositoryImpl: HelpRepositoryImpl
    ) : GetFromDbHelpItemsUseCase {
        return GetFromDbHelpItemsUseCase(helpRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbHelpItemsUseCase(
        helpRepositoryImpl: HelpRepositoryImpl
    ) : InsertToDbHelpItemsUseCase {
        return InsertToDbHelpItemsUseCase(helpRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbEventItemsByTitleUseCase(
        eventRepositoryImpl: EventRepositoryImpl
    ) : GetFromDbEventItemsByTitleUseCase {
        return GetFromDbEventItemsByTitleUseCase(eventRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbEventItemsUseCase(
        eventRepositoryImpl: EventRepositoryImpl
    ) : InsertToDbEventItemsUseCase {
        return InsertToDbEventItemsUseCase(eventRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbNkoItemsByTitleUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl
    ) : GetFromDbNkoItemsByTitleUseCase {
        return GetFromDbNkoItemsByTitleUseCase(nkoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbAllNkoItemsUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl
    ) : GetFromDbAllNkoItemsUseCase {
        return GetFromDbAllNkoItemsUseCase(nkoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbNkoItemsUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl
    ) : InsertToDbNkoItemsUseCase {
        return InsertToDbNkoItemsUseCase(nkoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbAllNewsPreviewItemsUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : GetFromDbAllNewsPreviewItemsUseCase {
        return GetFromDbAllNewsPreviewItemsUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbNewsDescriptionItemUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : GetFromDbNewsDescriptionItemByIdUseCase {
        return GetFromDbNewsDescriptionItemByIdUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbNewsItemsUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : InsertToDbNewsItemsUseCase {
        return InsertToDbNewsItemsUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbWatchedNewsIdUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : InsertToDbWatchedNewsIdUseCase {
        return InsertToDbWatchedNewsIdUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUnwatchedNewsNumberUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : GetUnwatchedNewsNumberUseCase {
        return GetUnwatchedNewsNumberUseCase(newsRepositoryImpl)
    }

    /** Application utilities */

    @Provides
    @Singleton
    fun provideRequestManager(
        @ApplicationContext appContext: Context
    ) : RequestManager {
        return Glide.with(appContext)
    }

    @Provides
    @Singleton
    fun providePresentationUtils(
        @ApplicationContext appContext: Context,
        requestManager: RequestManager
    ) : PresentationUtils {
        return PresentationUtils(appContext, requestManager)
    }

    @Provides
    @Singleton
    fun provideDataUtils(
        @ApplicationContext appContext: Context
    ) : DataUtils {
        return DataUtils(appContext)
    }

    companion object {
        private const val RETROFIT_BASE_URL = "https://help-app.getsandbox.com"
        private const val ROOM_DATABASE_FILE_NAME = "database.db"
    }

}
