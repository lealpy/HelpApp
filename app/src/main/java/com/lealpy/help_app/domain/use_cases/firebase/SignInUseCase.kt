package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(email: String, password: String): Completable {
        return repository.signIn(email, password)
    }

}
