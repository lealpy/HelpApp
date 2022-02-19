package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.repository.WorkerRepository
import javax.inject.Inject

class StartDonateNotificationWorkerUseCase @Inject constructor(
    private val repository: WorkerRepository
) {

    operator fun invoke(vararg pairs : Pair<String, Any?>) {
        repository.startDonateNotificationWorker(*pairs)
    }

}