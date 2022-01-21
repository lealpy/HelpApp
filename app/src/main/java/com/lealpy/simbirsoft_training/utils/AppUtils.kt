package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.RequestManager
import com.lealpy.simbirsoft_training.ui.help.HelpItem
import com.lealpy.simbirsoft_training.ui.help.HelpItemJSON
import com.lealpy.simbirsoft_training.ui.news.NewsItem
import com.lealpy.simbirsoft_training.ui.news.NewsItemJSON

object AppUtils {

    fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun cropBitmap(bitmap: Bitmap, ratio : Double) : Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            (bitmap.width / ratio).toInt()
        )
    }

    private fun getBitmapFromUrl(requestManager : RequestManager, url : String) : Bitmap {
        return requestManager
            .asBitmap()
            .load(url)
            .submit()
            .get()
    }

    fun newsItemsJsonToNewsItems(newsItemsJSON : List<NewsItemJSON>, requestManager : RequestManager) : List<NewsItem>{
        return newsItemsJSON.map { newsItemFromJSON ->

            val image = AppUtils.getBitmapFromUrl(requestManager, newsItemFromJSON.imageURL)

            NewsItem(
                id = newsItemFromJSON.id,
                image = image,
                title = newsItemFromJSON.title,
                abbreviatedText = newsItemFromJSON.abbreviatedText,
                date = newsItemFromJSON.date,
                fundName = null,
                address = null,
                phone = null,
                image2 = null,
                image3 = null,
                fullText = null,
                isChildrenCategory = newsItemFromJSON.isChildrenCategory,
                isAdultsCategory = newsItemFromJSON.isAdultsCategory,
                isElderlyCategory = newsItemFromJSON.isElderlyCategory,
                isAnimalsCategory = newsItemFromJSON.isAnimalsCategory,
                isEventsCategory = newsItemFromJSON.isEventsCategory
            )
        }
    }

    fun helpItemsJsonToHelpItems(
        helpItemsJSON: List<HelpItemJSON>,
        requestManager: RequestManager,
    ): List<HelpItem> {
        return helpItemsJSON.map { helpItemJSON ->
            val bitmap = requestManager
                .asBitmap()
                .load(helpItemJSON.imageURL)
                .submit()
                .get()

            HelpItem(
                id = helpItemJSON.id,
                image = bitmap,
                text = helpItemJSON.text
            )
        }
    }

}
