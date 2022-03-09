package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.EventRepositoryImpl
import com.lealpy.help_app.domain.use_cases.search_by_events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_events.UpdateEventItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesEventsModule {

    @Provides
    @Singleton
    fun provideGetFromDbEventItemsByTitleUseCase(
        eventRepositoryImpl: EventRepositoryImpl,
    ): GetFromDbEventItemsByTitleUseCase {
        return GetFromDbEventItemsByTitleUseCase(eventRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideUpdateEventItemsUseCase(
        eventRepositoryImpl: EventRepositoryImpl,
    ): UpdateEventItemsUseCase {
        return UpdateEventItemsUseCase(eventRepositoryImpl)
    }

}
