package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.ui.news.NewsItemJSON
import com.lealpy.simbirsoft_training.ui.news.NewsViewModel
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }
    val badgeSubject: PublishSubject<Int> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavView()
        if(savedInstanceState == null) getStartBadgeNumber()

    }

    private fun initBottomNavView() {
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigationNews -> showBottomNavView()
                R.id.navigationSearch -> showBottomNavView()
                R.id.navigationHelp -> showBottomNavView()
                R.id.navigationHistory -> showBottomNavView()
                R.id.navigationProfile -> showBottomNavView()
                else -> hideBottomNavView()
            }
        }

        badgeSubject.subscribe(
            { badgeNumber ->
                bottomNavView.getOrCreateBadge(
                    R.id.navigationNews
                ).apply {
                    number = badgeNumber
                    isVisible = number != 0
                }
            },
            { error ->
                throw Exception(error.message)
            }
        )

    }

    private fun getStartBadgeNumber() {
        Observable.create<Int> { emitter ->
            val jsonFileString = AppUtils.getJsonDataFromAsset(this, NewsViewModel.NEWS_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<NewsItemJSON>>() {}.type
            val newsItemsFromJson : List<NewsItemJSON> = gson.fromJson(jsonFileString, itemTypes)
            val startBadgeNumber = newsItemsFromJson.size

            if (!emitter.isDisposed) {
                emitter.onNext(startBadgeNumber)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { startBadgeNumber ->
                    badgeSubject.onNext(startBadgeNumber)
                },
                { error ->
                    throw java.lang.Exception(error.message)
                },
            )
    }

    private fun showBottomNavView() {
        bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNavView() {
        bottomNavView.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (
            navController.currentDestination?.id == R.id.navigationNews ||
            navController.currentDestination?.id == R.id.navigationSearch ||
            navController.currentDestination?.id == R.id.navigationHelp ||
            navController.currentDestination?.id == R.id.navigationHistory ||
            navController.currentDestination?.id == R.id.navigationProfile ||
            navController.currentDestination?.id == R.id.authorizationFragment
        ) {
            finish()
        }
        super.onBackPressed()
    }
}
