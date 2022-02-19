package com.lealpy.help_app.domain.repository

import com.lealpy.help_app.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface FireRepository {
    fun signUp(
        name : String,
        surname : String,
        dateOfBirth : Long,
        fieldOfActivity : String,
        email : String,
        password : String
    ) : Completable
    fun signIn(email : String, password : String) : Completable
    fun signOut() : Completable
    fun restorePassword(email : String) : Completable
    fun getUser() : Observable<User>
    fun getAuthState() : Single<Boolean>
    fun saveAvatarToStorage(byteArray : ByteArray) : Completable
}