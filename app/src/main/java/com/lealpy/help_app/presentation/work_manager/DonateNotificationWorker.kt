package com.lealpy.help_app.presentation.work_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.lealpy.help_app.R
import com.lealpy.help_app.presentation.MainActivity
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_ILLEGAL_DONATION_AMOUNT
import com.lealpy.help_app.presentation.utils.Const.ILLEGAL_ID
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_TITLE_KEY

class DonateNotificationWorker(
    context: Context,
    params: WorkerParameters,
) : Worker(context, params) {

    init {
        createNotificationChannel()
    }

    override fun doWork(): Result {
        val newsId =
            inputData.getLong(NEWS_FEATURE_NEWS_ID_KEY, ILLEGAL_ID)
        val newsTitle = inputData.getString(NEWS_FEATURE_NEWS_TITLE_KEY) ?: ""
        val donationAmount = inputData.getInt(DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY,
            DONATE_MONEY_FEATURE_ILLEGAL_DONATION_AMOUNT)
        val isFirstNotification =
            inputData.getBoolean(DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY, true)

        if (isFirstNotification) {
            sendFirstNotification(
                notificationId = generateNotificationId(),
                newsId = newsId,
                newsTitle = newsTitle,
                donationAmount = donationAmount
            )
        } else {
            sendRepeatedNotification(
                notificationId = generateNotificationId(),
                newsId = newsId,
                newsTitle = newsTitle,
                donationAmount = donationAmount
            )
        }

        return Result.success()
    }

    private fun sendFirstNotification(
        notificationId: Int,
        newsId: Long,
        newsTitle: String,
        donationAmount: Int,
    ) {
        val navigatePendingIntent = NavDeepLinkBuilder(applicationContext)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.newsDescriptionFragment)
            .setArguments(bundleOf(NEWS_FEATURE_NEWS_ID_KEY to newsId))
            .createPendingIntent()

        val broadcastIntent = Intent(
            applicationContext,
            RepeatDonateNotificationBroadcastReceiver::class.java
        ).apply {
            putExtra(NEWS_FEATURE_NEWS_ID_KEY, newsId)
            putExtra(NEWS_FEATURE_NEWS_TITLE_KEY, newsTitle)
            putExtra(DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY, donationAmount)
            putExtra(DONATE_MONEY_FEATURE_FIRST_NOTIFICATION_ID_KEY, notificationId)
        }

        val broadcastPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            broadcastIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationText = String.format(
            applicationContext.getString(R.string.donate_notification_worker_first_notification_text),
            donationAmount
        )

        val notification = NotificationCompat.Builder(applicationContext,
            DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_hands)
            .setContentTitle(newsTitle)
            .setContentText(notificationText)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText(notificationText)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setChannelId(DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL)
            .setContentIntent(navigatePendingIntent)
            .addAction(
                R.drawable.ic_baseline_notifications_24,
                applicationContext.getString(R.string.donate_notification_worker_button_remind),
                broadcastPendingIntent
            )
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, notification.build())
        }
    }

    private fun sendRepeatedNotification(
        notificationId: Int,
        newsId: Long,
        newsTitle: String,
        donationAmount: Int,
    ) {
        val navigatePendingIntent = NavDeepLinkBuilder(applicationContext)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.newsDescriptionFragment)
            .setArguments(bundleOf(NEWS_FEATURE_NEWS_ID_KEY to newsId))
            .createPendingIntent()

        val notificationText = String.format(
            applicationContext.getString(R.string.donate_notification_worker_repeated_notification_text),
            donationAmount
        )

        val notification = NotificationCompat.Builder(applicationContext,
            DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_hands)
            .setContentTitle(newsTitle)
            .setContentText(notificationText)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText(notificationText)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setChannelId(DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL)
            .setContentIntent(navigatePendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, notification.build())
        }
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL,
            DONATE_MONEY_FEATURE_NOTIFICATION_NAME,
            IMPORTANCE_HIGH
        )
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun generateNotificationId(): Int {
        val prefs = applicationContext.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val id = if (id.equals(Integer.MAX_VALUE)) {
            0
        } else {
            prefs.getInt(PREFS_ID_KEY, 0) + 1
        }
        prefs.edit().putInt(PREFS_ID_KEY, id).apply()
        return id
    }

    companion object {
        private const val PREFS_FILE_NAME = "donate_money_notification_ids"
        private const val PREFS_ID_KEY = "DONATE_MONEY_PREFS_NOTIFICATION_ID_KEY"
        private const val DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL =
            "DONATE_MONEY_FEATURE_NOTIFICATION_CHANNEL"
        private const val DONATE_MONEY_FEATURE_NOTIFICATION_NAME =
            "DONATE_MONEY_FEATURE_NOTIFICATION_NAME"
        private const val DONATE_MONEY_FEATURE_FIRST_NOTIFICATION_ID_KEY =
            "FIRST_NOTIFICATION_ID_KEY"
    }

}
