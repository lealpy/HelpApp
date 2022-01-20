package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.content.Intent
import android.app.IntentService
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.utils.AppUtils
import kotlin.collections.ArrayList

class GetNkoItemsFromJsonIntentService : IntentService(INTENT_SERVICE_NAME) {

    override fun onHandleIntent(intent: Intent?) {

        Thread.sleep(THREAD_SLEEP_MILLIS)

        val jsonFileString = AppUtils.getJsonDataFromAsset(application, NKO_ITEMS_JSON_FILE_NAME)
        val gson = Gson()
        val itemTypes = object : TypeToken<List<NkoItem>>() {}.type
        val nkoItemsFromJson : List<NkoItem> = gson.fromJson(jsonFileString, itemTypes)

        val responseIntent = Intent()
        responseIntent.action = INTENT_SERVICE_ACTION
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT)
        responseIntent.putParcelableArrayListExtra(EXTRA_KEY_OUT, nkoItemsFromJson as? ArrayList<Parcelable>)
        sendBroadcast(responseIntent)

    }

    companion object {
        const val INTENT_SERVICE_ACTION = "INTENT_SERVICE_ACTION"
        private const val INTENT_SERVICE_NAME = "GetNkoItemsFromJsonIntentService"
        private const val THREAD_SLEEP_MILLIS : Long = 2000
        private const val NKO_ITEMS_JSON_FILE_NAME = "nko_items.json"
        const val EXTRA_KEY_OUT = "EXTRA_OUT"
    }

}
