package com.lealpy.simbirsoft_training.ui.help

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HelpViewModel(application: Application): AndroidViewModel(application) {

    private val jsonFileString = getJsonDataFromAsset(application, HELP_ITEMS_JSON_FILE_NAME)
    private val gson = Gson()
    private val itemTypes = object : TypeToken<List<HelpItem>>() {}.type
    private val helpItemsFromJson : List<HelpItem> = gson.fromJson(jsonFileString, itemTypes)

    private val _helpItems = MutableLiveData( helpItemsFromJson )
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    companion object {
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
    }

}
