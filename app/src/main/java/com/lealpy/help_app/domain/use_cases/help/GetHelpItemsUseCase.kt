package com.lealpy.help_app.domain.use_cases.help

import com.lealpy.help_app.domain.model.HelpItem
import com.lealpy.help_app.domain.repository.HelpRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHelpItemsUseCase @Inject constructor(
    private val repository: HelpRepository,
) {

    operator fun invoke(): Single<List<HelpItem>> {
        return repository.getHelpItems()
    }

}
