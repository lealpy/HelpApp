package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.ui.search.SearchFragment
import java.util.*

class SearchByNkoViewModel(application: Application) : AndroidViewModel(application) {

    private val _nkoItems = MutableLiveData<List<NkoItem>> ()
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private fun getRandomNkoList() {

        val nkoNames = getApplication<Application>().resources.getStringArray(R.array.nko_items)

        var nkoItems = mutableListOf<NkoItem>()

        nkoNames.forEachIndexed { index, name ->
            nkoItems.add(NkoItem(index.toLong(), name))
        }

        nkoItems.shuffle()

        val randomListSize = Random().nextInt(nkoItems.size)

        nkoItems = nkoItems.filterIndexed { index, _ ->
            index <= randomListSize
        }.toMutableList()

        _nkoItems.value = nkoItems

    }

    fun onTabSelected(position: Int) {
        when(position) {
            SearchFragment.POSITION_SEARCH_BY_NKO -> getRandomNkoList()
            else -> Unit
        }
    }

}
