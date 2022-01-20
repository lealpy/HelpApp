package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.ui.search.SearchFragment
import java.util.*

class SearchByNkoViewModel(application: Application) : AndroidViewModel(application) {

    private val _nkoItems = MutableLiveData<List<NkoItem>> ()
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private var nkoItemsFromJSON : List<NkoItem>? = null

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val getNkoItemsFromJsonBroadcastReceiver = GetNkoItemsFromJsonBroadcastReceiver()

    private fun getNkoItemsFromJSON() {

        _progressBarVisibility.value = View.VISIBLE

        val intentMyIntentService = Intent(getApplication(), GetNkoItemsFromJsonIntentService::class.java)
        getApplication<Application>().startService(intentMyIntentService)

        val intentFilter = IntentFilter(GetNkoItemsFromJsonIntentService.INTENT_SERVICE_ACTION)

        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        getApplication<Application>().registerReceiver(getNkoItemsFromJsonBroadcastReceiver, intentFilter)

    }

    private fun getRandomNkoItems() {
        var randomNkoItems = nkoItemsFromJSON?.toMutableList()
        if(randomNkoItems != null) {
            randomNkoItems.shuffle()
            val randomListSize = Random().nextInt(randomNkoItems.size)
            randomNkoItems = randomNkoItems.filterIndexed { index, _ ->
                index <= randomListSize
            }.toMutableList()
            _nkoItems.value = randomNkoItems
        }
    }

    fun onTabSelected(position: Int) {
        when(position) {
            SearchFragment.POSITION_SEARCH_BY_NKO -> {
                if(nkoItemsFromJSON == null) getNkoItemsFromJSON()
                else getRandomNkoItems()
            }
            else -> Unit
        }
    }

    fun onRefreshSwiped() {
        getNkoItemsFromJSON()
    }

    inner class GetNkoItemsFromJsonBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getParcelableArrayListExtra<NkoItem>(GetNkoItemsFromJsonIntentService.EXTRA_KEY_OUT)
            if (result != null) nkoItemsFromJSON = result
            getRandomNkoItems()
            _progressBarVisibility.value = View.GONE
        }
    }

}
