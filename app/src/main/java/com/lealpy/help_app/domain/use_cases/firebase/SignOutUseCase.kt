package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(): Completable {
        return repository.signOut()
    }

}