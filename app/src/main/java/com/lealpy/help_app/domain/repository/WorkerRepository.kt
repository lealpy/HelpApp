package com.lealpy.help_app.domain.repository

interface WorkerRepository {
    fun startDonateNotificationWorker(vararg pairs : Pair<String, Any?>)
}