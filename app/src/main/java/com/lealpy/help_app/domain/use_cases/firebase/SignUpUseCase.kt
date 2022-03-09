package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(
        name: String,
        surname: String,
        dateOfBirth: Long,
        fieldOfActivity: String,
        email: String,
        password: String,
    ): Completable {
        return repository.signUp(
            name = name,
            surname = surname,
            dateOfBirth = dateOfBirth,
            fieldOfActivity = fieldOfActivity,
            email = email,
            password = password
        )
    }

}
