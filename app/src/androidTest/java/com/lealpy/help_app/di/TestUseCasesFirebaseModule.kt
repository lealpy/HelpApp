package com.lealpy.help_app.di

import com.lealpy.help_app.domain.use_cases.firebase.*
import com.lealpy.help_app.data.repository.TestFireRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCasesFirebaseModule::class]
)
class TestUseCasesFirebaseModule {

    @Provides
    @Singleton
    fun provideSignInUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): SignInUseCase {
        return SignInUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): SignUpUseCase {
        return SignUpUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): SignOutUseCase {
        return SignOutUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): GetUserUseCase {
        return GetUserUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideSaveAvatarToStorageUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): SaveAvatarToStorageUseCase {
        return SaveAvatarToStorageUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideRestorePasswordUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): RestorePasswordUseCase {
        return RestorePasswordUseCase(testFireRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetAuthStateUseCase(
        testFireRepositoryImpl: TestFireRepositoryImpl,
    ): GetAuthStateUseCase {
        return GetAuthStateUseCase(testFireRepositoryImpl)
    }

}
