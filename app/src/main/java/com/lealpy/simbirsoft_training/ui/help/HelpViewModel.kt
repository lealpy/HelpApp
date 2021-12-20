package com.lealpy.simbirsoft_training.ui.help

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.utils.AppUtils

class HelpViewModel(application: Application): AndroidViewModel(application) {

    private val jsonFileString = AppUtils.getJsonDataFromAsset(application, HELP_ITEMS_JSON_FILE_NAME)
    private val gson = Gson()
    private val itemTypes = object : TypeToken<List<HelpItem>>() {}.type
    private val helpItemsFromJson : List<HelpItem> = gson.fromJson(jsonFileString, itemTypes)

    private val _helpItems = MutableLiveData( helpItemsFromJson )
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    companion object {
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
    }

}
