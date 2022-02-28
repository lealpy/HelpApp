package com.lealpy.help_app.data.repository

import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class TestFireRepositoryImpl : FireRepository {

    override fun signUp(
        name: String,
        surname: String,
        dateOfBirth: Long,
        fieldOfActivity: String,
        email: String,
        password: String,
    ): Completable {
        return Completable.complete()
    }

    override fun signIn(email: String, password: String): Completable {
        return Completable.complete()
    }

    override fun signOut(): Completable {
        return Completable.complete()
    }

    override fun restorePassword(email: String): Completable {
        return Completable.complete()
    }

    override fun getUser(): Single<User> {
        return Single.just(TEST_USER)
    }

    override fun getAuthState(): Single<Boolean> {
        return Single.just(true)
    }

    override fun saveAvatarToStorage(byteArray: ByteArray): Completable {
        return Completable.complete()
    }

    companion object {
        val TEST_USER = User(
            id = "TEST_ID",
            name = "Валерий",
            surname = "Ласточкин",
            dateOfBirth = 892598400000L,
            fieldOfActivity = "Медицина",
            email = "v.lastochkin@mail.ru",
            avatarUrl = "https://google.com/"
        )
    }

}
