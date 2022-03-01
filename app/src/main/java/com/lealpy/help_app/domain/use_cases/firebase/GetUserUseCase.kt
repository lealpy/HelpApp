package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(): Single<User> {
        return repository.getUser()
    }

}
