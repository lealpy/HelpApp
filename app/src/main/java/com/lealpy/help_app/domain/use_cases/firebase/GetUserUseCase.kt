package com.lealpy.help_app.domain.use_cases.firebase

import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.domain.repository.FireRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: FireRepository
) {

    operator fun invoke() : Observable<User> {
        return repository.getUser()
    }

}