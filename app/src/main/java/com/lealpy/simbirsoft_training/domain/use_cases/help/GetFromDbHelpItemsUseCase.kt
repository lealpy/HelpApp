package com.lealpy.simbirsoft_training.domain.use_cases.help

import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.domain.repository.HelpRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFromDbHelpItemsUseCase @Inject constructor(
    private val repository: HelpRepository
) {

    fun execute(): Single<List<HelpItem>> {
        return repository.getFromDbHelpItems()
    }

}
