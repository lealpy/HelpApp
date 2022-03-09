package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(): Single<Boolean> {
        return repository.getAuthState()
    }

}
