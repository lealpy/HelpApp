package com.lealpy.simbirsoft_training.ui.help

import android.content.Context
import android.os.AsyncTask
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.ui.help.HelpFragment.Companion.HELP_ITEMS_JSON_FILE_NAME
import com.lealpy.simbirsoft_training.utils.AppUtils
import java.lang.ref.WeakReference

class GetHelpItemsAsyncTask(
    private val asyncTaskResponse : AsyncTaskResponse,
    private val contextRef : WeakReference<Context>
) : AsyncTask<Unit, Unit, List<HelpItem>>() {

    private val requestManager = contextRef.get()?.let { Glide.with(it) }

    override fun onPreExecute() {
        asyncTaskResponse.preExecute()
    }

    override fun doInBackground(vararg params: Unit?): List<HelpItem> {
        val context = contextRef.get()
        return if(context != null && requestManager != null) {
            Thread.sleep(HelpFragment.THREAD_SLEEP_MILLIS)
            val jsonFileString = AppUtils.getJsonDataFromAsset(context, HELP_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<HelpItemJSON>>() {}.type
            val helpItemsFromJSON = gson.fromJson<List<HelpItemJSON>>(jsonFileString, itemTypes)

            val helpItemsResult = AppUtils.helpItemsJsonToHelpItems(helpItemsFromJSON, requestManager)
            helpItemsResult
        }
        else emptyList()
    }

    override fun onPostExecute(helpItemsResult: List<HelpItem>?) {
        asyncTaskResponse.postExecute(helpItemsResult)
    }

}
