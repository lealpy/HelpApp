package com.lealpy.simbirsoft_training.ui.help

import android.content.Context
import android.os.AsyncTask
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.utils.AppUtils
import java.lang.ref.WeakReference

class GetHelpItemsAsyncTask(
    private val asyncTaskResponse : AsyncTaskResponse,
    private val contextRef : WeakReference<Context>
) : AsyncTask<Unit, Unit, List<HelpItem>>() {

    override fun onPreExecute() {
        asyncTaskResponse.preExecute()
    }

    override fun doInBackground(vararg params: Unit?): List<HelpItem> {
        val context = contextRef.get()
        return if(context != null) {
            Thread.sleep(HelpFragment.THREAD_SLEEP_MILLIS)
            val jsonFileString = AppUtils.getJsonDataFromAsset(context,
                HelpFragment.HELP_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<HelpItemJSON>>() {}.type
            val helpItemsFromJSON = gson.fromJson<List<HelpItemJSON>>(jsonFileString, itemTypes)

            val helpItemsResult = helpItemsFromJSON.map { helpItemFromJSON ->

                val bitmap = Glide
                    .with(context)
                    .asBitmap()
                    .load(helpItemFromJSON.imageURL)
                    .submit()
                    .get()

                HelpItem(
                    id = helpItemFromJSON.id,
                    image = bitmap,
                    text = helpItemFromJSON.text
                )

            }
            helpItemsResult
        }
        else emptyList()
    }

    override fun onPostExecute(helpItemsResult: List<HelpItem>?) {
        asyncTaskResponse.postExecute(helpItemsResult)
    }

}
