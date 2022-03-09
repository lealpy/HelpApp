package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class EditUserUseCase @Inject constructor(
    private val repository: FireRepository,
) {

    operator fun invoke(
        surname: String,
        name: String,
        dateOfBirth: Long,
        fieldOfActivity: String,
    ): Completable {
        return repository.editUser(
            surname = surname,
            name = name,
            dateOfBirth = dateOfBirth,
            fieldOfActivity = fieldOfActivity,
        )
    }

}
