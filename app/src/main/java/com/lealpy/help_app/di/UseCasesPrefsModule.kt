package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.PrefsRepositoryImpl
import com.lealpy.help_app.domain.use_cases.prefs.GetSettingGetPushUseCase
import com.lealpy.help_app.domain.use_cases.prefs.SetSettingGetPushUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesPrefsModule {

    @Provides
    @Singleton
    fun provideGetSettingGetPushUseCase(
        prefsRepositoryImpl: PrefsRepositoryImpl,
    ): GetSettingGetPushUseCase {
        return GetSettingGetPushUseCase(prefsRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSetSettingGetPushUseCase(
        prefsRepositoryImpl: PrefsRepositoryImpl,
    ): SetSettingGetPushUseCase {
        return SetSettingGetPushUseCase(prefsRepositoryImpl)
    }

}
