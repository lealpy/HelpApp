package com.lealpy.simbirsoft_training.presentation.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lealpy.simbirsoft_training.App
import com.lealpy.simbirsoft_training.presentation.MainActivity
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.model.NewsItemJson
import com.lealpy.simbirsoft_training.presentation.news.NewsViewModel.Companion.NEWS_ITEMS_JSON_FILE_NAME
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.disposables.CompositeDisposable

class SplashScreenActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getBadgeNumberAndStartMainActivity()
    }

    private fun getBadgeNumberAndStartMainActivity() {
        val newsApi = (application as? App)?.newsApi
        newsApi?.let {
            compositeDisposable.add(newsApi
                .getNewsItemsJson()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.schedulers.Schedulers.computation())
                .subscribe(
                    { newsItemsJsonFromServer ->
                        val startBadgeNumber = newsItemsJsonFromServer.size
                        startMainActivity(startBadgeNumber)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }
                        val newsItemsJsonFromFile = AppUtils.getItemJsonFromFile<List<NewsItemJson>>(this, NEWS_ITEMS_JSON_FILE_NAME)
                        val startBadgeNumber = newsItemsJsonFromFile.size
                        startMainActivity(startBadgeNumber)
                    }
                )
            )
        }

    }

    private fun startMainActivity(startBadgeNumber : Int) {
        Thread.sleep(SCREEN_TIME_MILLIS)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_KEY, startBadgeNumber)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SCREEN_TIME_MILLIS = 500L
        const val EXTRA_KEY = "com.lealpy.simbirsoft_training.presentation.splash_screen.SplashScreenActivity.EXTRA_KEY"
    }
}
