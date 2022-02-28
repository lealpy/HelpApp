package com.lealpy.help_app.di

import com.lealpy.help_app.data.repository.FireRepositoryImpl
import com.lealpy.help_app.domain.use_cases.firebase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesFirebaseModule {

    @Provides
    @Singleton
    fun provideSignInUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): SignInUseCase {
        return SignInUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): SignUpUseCase {
        return SignUpUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): SignOutUseCase {
        return SignOutUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): GetUserUseCase {
        return GetUserUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSaveAvatarToStorageUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): SaveAvatarToStorageUseCase {
        return SaveAvatarToStorageUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideRestorePasswordUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): RestorePasswordUseCase {
        return RestorePasswordUseCase(fireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetAuthStateUseCase(
        fireRepositoryImpl: FireRepositoryImpl,
    ): GetAuthStateUseCase {
        return GetAuthStateUseCase(fireRepositoryImpl)
    }

}
