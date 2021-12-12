package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R
import java.util.*

class SearchByNkoViewModel(application: Application) : AndroidViewModel(application) {

    private val _nkoItems = MutableLiveData ( getRandomNkoList(application) )
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private fun getRandomNkoList(application: Application) : List<NkoItem> {
        val nkoNames = application.resources.getStringArray(R.array.nko_items)

        var nkoItems = mutableListOf<NkoItem>()

        nkoNames.forEachIndexed { index, name ->
            nkoItems.add(NkoItem(index.toLong(), name))
        }

        nkoItems.shuffle()

        val randomListSize = Random().nextInt(nkoItems.size)

        nkoItems = nkoItems.filterIndexed { index, _ ->
            index <= randomListSize
        }.toMutableList()

        return nkoItems
    }

}
