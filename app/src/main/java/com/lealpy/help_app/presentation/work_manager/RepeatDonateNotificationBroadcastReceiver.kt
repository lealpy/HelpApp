package com.lealpy.help_app.presentation.work_manager

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_ILLEGAL_DONATION_AMOUNT
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_ILLEGAL_NEWS_ID
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_TITLE_KEY

class RepeatDonateNotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val newsId =
            intent?.getLongExtra(NEWS_FEATURE_NEWS_ID_KEY, DONATE_MONEY_FEATURE_ILLEGAL_NEWS_ID)
        val newsTitle = intent?.getStringExtra(NEWS_FEATURE_NEWS_TITLE_KEY)
        val donationAmount = intent?.getIntExtra(DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY,
            DONATE_MONEY_FEATURE_ILLEGAL_DONATION_AMOUNT)
        val firstNotificationId =
            intent?.getIntExtra(DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY,
                ILLEGAL_NOTIFICATION_ID)

        if (
            context != null &&
            newsId != null &&
            newsTitle != null &&
            donationAmount != null &&
            firstNotificationId != null
        ) {
            startWorkManager(context, newsId, newsTitle, donationAmount)

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.cancel(firstNotificationId)
        }
    }

    private fun startWorkManager(
        context: Context,
        newsId: Long,
        newsTitle: String,
        donationAmount: Int,
    ) {
        val inputData = workDataOf(
            NEWS_FEATURE_NEWS_ID_KEY to newsId,
            NEWS_FEATURE_NEWS_TITLE_KEY to newsTitle,
            DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY to donationAmount,
            DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY to false
        )

        val workRequest = OneTimeWorkRequestBuilder<DonateNotificationWorker>()
            //.setInitialDelay(REPEATED_NOTIFICATION_DELAY, TimeUnit.MINUTES)
            .setInputData(inputData)
            .build()

        WorkManager
            .getInstance(context.applicationContext)
            .enqueue(workRequest)
    }

    companion object {
        private const val REPEATED_NOTIFICATION_DELAY = 30L
        private const val ILLEGAL_NOTIFICATION_ID = -1
    }

}
