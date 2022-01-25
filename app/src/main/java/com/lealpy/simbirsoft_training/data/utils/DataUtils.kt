package com.lealpy.simbirsoft_training.data.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class DataUtils @Inject constructor(
    val appContext : Context
) {
    inline fun <reified T> getItemsFromFile(fileName: String) : T {
        val jsonFileString = appContext.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonFileString, object: TypeToken<T>() {}.type)
    }
    companion object {
        const val LOG_TAG = "HelpAppLog"
    }
}