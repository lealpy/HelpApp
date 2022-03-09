package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.NkoRepositoryImpl
import com.lealpy.help_app.domain.use_cases.search_by_nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_nko.UpdateNkoItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNkoModule {

    @Provides
    @Singleton
    fun provideGetFromDbNkoItemsByTitleUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl,
    ): GetFromDbNkoItemsByTitleUseCase {
        return GetFromDbNkoItemsByTitleUseCase(nkoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideUpdateNkoItemsUseCase(
        nkoRepositoryImpl: NkoRepositoryImpl,
    ): UpdateNkoItemsUseCase {
        return UpdateNkoItemsUseCase(nkoRepositoryImpl)
    }

}
