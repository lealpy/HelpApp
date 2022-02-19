package com.lealpy.help_app.data.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.lealpy.help_app.domain.repository.WorkerRepository
import com.lealpy.help_app.presentation.work_manager.DonateNotificationWorker
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
    private val appContext: Context,
) : WorkerRepository {
    override fun startDonateNotificationWorker(vararg pairs: Pair<String, Any?>) {
        val constraints = Constraints.Builder().build()

        val inputData = workDataOf(*pairs)

        val workRequest = OneTimeWorkRequestBuilder<DonateNotificationWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        WorkManager
            .getInstance(appContext)
            .enqueue(workRequest)
    }
}