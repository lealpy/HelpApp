package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.HelpRepositoryImpl
import com.lealpy.help_app.domain.use_cases.help.GetHelpItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesHelpModule {

    /** Help use cases */

    @Provides
    @Singleton
    fun provideGetHelpItemsUseCase(
        helpRepositoryImpl: HelpRepositoryImpl,
    ): GetHelpItemsUseCase {
        return GetHelpItemsUseCase(helpRepositoryImpl)
    }

}
