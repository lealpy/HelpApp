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
import com.lealpy.simbirsoft_training.data.database.events.EventDao
import com.lealpy.simbirsoft_training.data.repository.EventRepositoryImpl
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.repository.HelpRepositoryImpl
import com.lealpy.simbirsoft_training.data.utils.DataUtils
import com.lealpy.simbirsoft_training.domain.use_cases.events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.events.SaveToDbEventItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.help.GetFromDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.help.SaveToDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.ResourceManager
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
    fun provideEventDao(appDatabase: AppDatabase) : EventDao {
        return appDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideHelpDao(appDatabase: AppDatabase) : HelpDao {
        return appDatabase.helpDao()
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
    fun provideSaveToDbHelpItemsUseCase(
        helpRepositoryImpl: HelpRepositoryImpl
    ) : SaveToDbHelpItemsUseCase {
        return SaveToDbHelpItemsUseCase(helpRepositoryImpl)
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
    fun provideSaveToDbEventItemsUseCase(
        eventRepositoryImpl: EventRepositoryImpl
    ) : SaveToDbEventItemsUseCase {
        return SaveToDbEventItemsUseCase(eventRepositoryImpl)
    }

    /** Resource manager */

    @Provides
    @Singleton
    fun provideResourceManager(
        @ApplicationContext appContext: Context
    ): ResourceManager {
        return ResourceManager(appContext.resources)
    }

    /** Request manager */

    @Provides
    @Singleton
    fun provideRequestManager(
        @ApplicationContext appContext: Context
    ) : RequestManager {
        return Glide.with(appContext)
    }

    /** Application utilities */

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
