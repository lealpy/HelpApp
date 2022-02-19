package com.lealpy.help_app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.lealpy.help_app.data.api.EventApi
import com.lealpy.help_app.data.api.HelpApi
import com.lealpy.help_app.data.api.NewsApi
import com.lealpy.help_app.data.api.NkoApi
import com.lealpy.help_app.data.database.AppDatabase
import com.lealpy.help_app.data.database.search_by_events.EventDao
import com.lealpy.help_app.data.database.help.HelpDao
import com.lealpy.help_app.data.database.news.NewsDao
import com.lealpy.help_app.data.database.search_by_nko.NkoDao
import com.lealpy.help_app.data.repository.*
import com.lealpy.help_app.data.utils.DataUtils
import com.lealpy.help_app.domain.use_cases.firebase.*
import com.lealpy.help_app.domain.use_cases.search_by_events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_events.UpdateEventItemsUseCase
import com.lealpy.help_app.domain.use_cases.help.GetHelpItemsUseCase
import com.lealpy.help_app.domain.use_cases.news.*
import com.lealpy.help_app.domain.use_cases.search_by_nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_nko.UpdateNkoItemsUseCase
import com.lealpy.help_app.domain.use_cases.prefs.GetSettingGetPushUseCase
import com.lealpy.help_app.domain.use_cases.prefs.SetSettingGetPushUseCase
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import com.lealpy.help_app.presentation.utils.PresentationValidators
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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

    @Provides
    @Singleton
    fun providePrefsRepository(
        prefs : SharedPreferences
    ) : PrefsRepositoryImpl {
        return PrefsRepositoryImpl(prefs)
    }

    @Provides
    @Singleton
    fun provideWorkerRepository(
        @ApplicationContext appContext : Context
    ) : WorkerRepositoryImpl {
        return WorkerRepositoryImpl(appContext)
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    /** Firebase */

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return Firebase.database(FIREBASE_DATABASE_URL)
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    /** Shared preferences */

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    /** Help use cases */

    @Provides
    @Singleton
    fun provideGetHelpItemsUseCase(
        helpRepositoryImpl: HelpRepositoryImpl
    ) : GetHelpItemsUseCase {
        return GetHelpItemsUseCase(helpRepositoryImpl)
    }

    /** Event use cases */

    @Provides
    @Singleton
    fun provideGetFromDbEventItemsByTitleUseCase(
        eventRepositoryImpl: EventRepositoryImpl
    ) : GetFromDbEventItemsByTitleUseCase {
        return GetFromDbEventItemsByTitleUseCase(eventRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideUpdateEventItemsUseCase(
        eventRepositoryImpl: EventRepositoryImpl
    ) : UpdateEventItemsUseCase {
        return UpdateEventItemsUseCase(eventRepositoryImpl)
    }

    /** Nko use cases */

    @Provides
    @Singleton
    fun provideGetFromDbNkoItemsByTitleUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl
    ) : GetFromDbNkoItemsByTitleUseCase {
        return GetFromDbNkoItemsByTitleUseCase(nkoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideUpdateNkoItemsUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl
    ) : UpdateNkoItemsUseCase {
        return UpdateNkoItemsUseCase(nkoRepositoryImpl)
    }

    /** News use cases */

    @Provides
    @Singleton
    fun provideGetFromDbAllNewsPreviewItemsUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : GetNewsPreviewItemsUseCase {
        return GetNewsPreviewItemsUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbNewsDescriptionItemUseCase(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : GetNewsDescriptionItemUseCase {
        return GetNewsDescriptionItemUseCase(newsRepositoryImpl)
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

    @Provides
    @Singleton
    fun provideStartStartDonateNotificationWorkerUseCase(
        workerRepositoryImpl: WorkerRepositoryImpl
    ) : StartDonateNotificationWorkerUseCase {
        return StartDonateNotificationWorkerUseCase(workerRepositoryImpl)
    }

    /** Prefs use cases */

    @Provides
    @Singleton
    fun provideGetSettingGetPushUseCase(
        prefsRepositoryImpl: PrefsRepositoryImpl
    ) : GetSettingGetPushUseCase {
        return GetSettingGetPushUseCase(prefsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSetSettingGetPushUseCase(
        prefsRepositoryImpl: PrefsRepositoryImpl
    ) : SetSettingGetPushUseCase {
        return SetSettingGetPushUseCase(prefsRepositoryImpl)
    }

    /** Firebase use cases */

    @Provides
    @Singleton
    fun provideSignInUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : SignInUseCase {
        return SignInUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : SignUpUseCase {
        return SignUpUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : SignOutUseCase {
        return SignOutUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : GetUserUseCase {
        return GetUserUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSaveAvatarToStorageUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : SaveAvatarToStorageUseCase {
        return SaveAvatarToStorageUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideRestorePasswordUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : RestorePasswordUseCase {
        return RestorePasswordUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetAuthStateUseCase(
        fireRepositoryImpl: FireRepositoryImpl
    ) : GetAuthStateUseCase {
        return GetAuthStateUseCase(fireRepositoryImpl)
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
    fun providePresentationMappers(
        @ApplicationContext appContext: Context,
        presentationUtils: PresentationUtils
    ) : PresentationMappers {
        return PresentationMappers(appContext, presentationUtils)
    }

    @Provides
    @Singleton
    fun providePresentationValidators() : PresentationValidators {
        return PresentationValidators()
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
        private const val SHARED_PREFERENCES_FILE_NAME = "prefs"
        private const val FIREBASE_DATABASE_URL = "https://help-app-b05d1-default-rtdb.europe-west1.firebasedatabase.app"
    }

}
