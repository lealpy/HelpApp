package com.lealpy.simbirsoft_training.domain.use_cases.nko

import com.lealpy.simbirsoft_training.domain.model.NkoItem
import com.lealpy.simbirsoft_training.domain.repository.NkoRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFromDbAllNkoItemsUseCase @Inject constructor(
    private val repository : NkoRepository
) {

    fun execute() : Single<List<NkoItem>> {
        return repository.getFromDbAllNkoItems()
    }

}