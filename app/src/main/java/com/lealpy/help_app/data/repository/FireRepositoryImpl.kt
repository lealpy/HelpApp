package com.lealpy.help_app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FireRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage,
) : FireRepository {

    override fun signUp(
        name: String,
        surname: String,
        dateOfBirth: Long,
        fieldOfActivity: String,
        email: String,
        password: String,
    ): Completable {
        return Completable.create { emitter ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        firebaseAuth.currentUser?.uid?.let { uid ->
                            val user = User(
                                id = uid,
                                name = name,
                                surname = surname,
                                dateOfBirth = dateOfBirth,
                                fieldOfActivity = fieldOfActivity,
                                email = email,
                                avatarUrl = ""
                            )

                            firebaseDatabase.getReference(DATABASE_USERS_PATH)
                                .child(uid)
                                .setValue(user)
                                .addOnCompleteListener { databaseTask ->
                                    if (databaseTask.isSuccessful) {
                                        emitter.onComplete()
                                    } else {
                                        databaseTask.exception?.let { emitter.onError(it) }
                                    }
                                }
                        }
                    } else {
                        authTask.exception?.let { emitter.onError(it) }
                    }
                }
        }
    }

    override fun signIn(email: String, password: String): Completable {
        return Completable.create { emitter ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        task.exception?.let { emitter.onError(it) }
                    }
                }
        }
    }

    override fun signOut(): Completable {
        return Completable.create { emitter ->
            firebaseAuth.signOut()
            emitter.onComplete()
        }
    }

    override fun restorePassword(email: String): Completable {
        return Completable.create { emitter ->
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { restorePasswordTask ->
                    if (restorePasswordTask.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        restorePasswordTask.exception?.let { emitter.onError(it) }
                    }
                }
        }
    }

    override fun getUser(): Single<User> {
        return Single.create { emitter ->
            firebaseAuth.currentUser?.uid?.let { uid ->
                firebaseDatabase.getReference(DATABASE_USERS_PATH)
                    .child(uid)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        val user = snapshot.getValue<User>()
                        user?.let { emitter.onSuccess(it) }
                            ?: emitter.onError(NullPointerException())
                    }
                    .addOnFailureListener { exception ->
                        emitter.onError(exception)
                    }
            }
        }
    }

    override fun getAuthState(): Single<Boolean> {
        return Single.just(firebaseAuth.currentUser?.uid != null)
    }

    override fun saveAvatarToStorage(byteArray: ByteArray): Completable {
        return Completable.create { emitter ->
            firebaseAuth.currentUser?.uid?.let { uid ->
                val reference = firebaseStorage.reference.child("$STORAGE_AVATAR_PATH/$uid.jpg")

                reference
                    .putBytes(byteArray)
                    .continueWithTask {
                        reference.downloadUrl
                    }
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val avatarUrl = task.result.toString()

                            firebaseAuth.currentUser?.uid?.let { uid ->
                                firebaseDatabase.getReference(DATABASE_USERS_PATH)
                                    .child("$uid/$DATABASE_AVATAR_URL_PATH")
                                    .setValue(avatarUrl)
                                    .addOnCompleteListener { databaseTask ->
                                        if (databaseTask.isSuccessful) {
                                            emitter.onComplete()
                                        } else {
                                            databaseTask.exception?.let { emitter.onError(it) }
                                        }
                                    }
                            }
                        } else {
                            task.exception?.let { emitter.onError(it) }
                        }
                    }
            }
        }
    }

    override fun editUser(
        surname: String,
        name: String,
        dateOfBirth: Long,
        fieldOfActivity: String,
    ): Completable {
        return getUser()
            .flatMapCompletable { user ->
                val editedUser = User(
                    id = user.id,
                    name = name,
                    surname = surname,
                    dateOfBirth = dateOfBirth,
                    fieldOfActivity = fieldOfActivity,
                    email = user.email,
                    avatarUrl = user.avatarUrl,
                )

                Completable.create { emitter ->
                    firebaseAuth.currentUser?.uid?.let { uid ->
                        firebaseDatabase.getReference(DATABASE_USERS_PATH)
                            .child(uid)
                            .setValue(editedUser)
                            .addOnCompleteListener { databaseTask ->
                                if (databaseTask.isSuccessful) {
                                    emitter.onComplete()
                                } else {
                                    databaseTask.exception?.let { emitter.onError(it) }
                                }
                            }
                    }
                }
            }
    }

    companion object {
        private const val DATABASE_USERS_PATH = "users"
        private const val DATABASE_AVATAR_URL_PATH = "avatarUrl"
        private const val STORAGE_AVATAR_PATH = "avatars"
    }

}