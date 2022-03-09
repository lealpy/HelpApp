package com.lealpy.help_app.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.lealpy.help_app.data.utils.DataUtils
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import com.lealpy.help_app.presentation.utils.PresentationValidators
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    @Singleton
    fun provideRequestManager(
        @ApplicationContext appContext: Context,
    ): RequestManager {
        return Glide.with(appContext)
    }

    @Provides
    @Singleton
    fun providePresentationUtils(
        @ApplicationContext appContext: Context,
        requestManager: RequestManager,
    ): PresentationUtils {
        return PresentationUtils(appContext, requestManager)
    }

    @Provides
    @Singleton
    fun providePresentationMappers(
        @ApplicationContext appContext: Context,
        presentationUtils: PresentationUtils,
    ): PresentationMappers {
        return PresentationMappers(appContext, presentationUtils)
    }

    @Provides
    @Singleton
    fun providePresentationValidators(): PresentationValidators {
        return PresentationValidators()
    }

    @Provides
    @Singleton
    fun provideDataUtils(
        @ApplicationContext appContext: Context,
    ): DataUtils {
        return DataUtils(appContext)
    }

}
