package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.NewsRepositoryImpl
import com.lealpy.help_app.data.repository.WorkerRepositoryImpl
import com.lealpy.help_app.domain.use_cases.news.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNewsModule {

    @Provides
    @Singleton
    fun provideGetFromDbAllNewsPreviewItemsUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): GetNewsPreviewItemsUseCase {
        return GetNewsPreviewItemsUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbNewsDescriptionItemUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): GetNewsDescriptionItemUseCase {
        return GetNewsDescriptionItemUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbWatchedNewsIdUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): InsertToDbWatchedNewsIdUseCase {
        return InsertToDbWatchedNewsIdUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUnwatchedNewsNumberUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): GetUnwatchedNewsNumberUseCase {
        return GetUnwatchedNewsNumberUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideStartStartDonateNotificationWorkerUseCase(
        workerRepositoryImpl: WorkerRepositoryImpl,
    ): StartDonateNotificationWorkerUseCase {
        return StartDonateNotificationWorkerUseCase(workerRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideAddDonationHistoryItemUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): AddDonationHistoryItemUseCase {
        return AddDonationHistoryItemUseCase(newsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetDonationHistoryItemsUseCase(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): GetDonationHistoryItemsUseCase {
        return GetDonationHistoryItemsUseCase(newsRepositoryImpl)
    }

}
