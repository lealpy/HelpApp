package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.TestNewsRepositoryImpl
import com.lealpy.help_app.data.repository.WorkerRepositoryImpl
import com.lealpy.help_app.domain.use_cases.news.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCasesNewsModule::class]
)
class TestUseCasesNewsModule {

    @Provides
    @Singleton
    fun provideGetFromDbAllNewsPreviewItemsUseCase(
        testNewsRepositoryImpl: TestNewsRepositoryImpl,
    ): GetNewsPreviewItemsUseCase {
        return GetNewsPreviewItemsUseCase(testNewsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetFromDbNewsDescriptionItemUseCase(
        testNewsRepositoryImpl: TestNewsRepositoryImpl,
    ): GetNewsDescriptionItemUseCase {
        return GetNewsDescriptionItemUseCase(testNewsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideInsertToDbWatchedNewsIdUseCase(
        testNewsRepositoryImpl: TestNewsRepositoryImpl,
    ): InsertToDbWatchedNewsIdUseCase {
        return InsertToDbWatchedNewsIdUseCase(testNewsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUnwatchedNewsNumberUseCase(
        testNewsRepositoryImpl: TestNewsRepositoryImpl,
    ): GetUnwatchedNewsNumberUseCase {
        return GetUnwatchedNewsNumberUseCase(testNewsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideStartStartDonateNotificationWorkerUseCase(
        workerRepositoryImpl: WorkerRepositoryImpl,
    ): StartDonateNotificationWorkerUseCase {
        return StartDonateNotificationWorkerUseCase(workerRepositoryImpl)
    }

}
