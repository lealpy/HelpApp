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

    fun getBitmapFromUrl(requestManager : RequestManager, url : String) : Bitmap {
        return requestManager
            .asBitmap()
            .load(url)
            .submit()
            .get()
    }

    fun newsItemsJsonToNewsItems(
        newsItemsJSON: List<NewsItemJSON>,
        requestManager: RequestManager,
    ): List<NewsItem> {
        return newsItemsJSON.map { newsItemJSON ->
            val image = getBitmapFromUrl(requestManager, newsItemJSON.imageURL)
            val image2 = getBitmapFromUrl(requestManager, newsItemJSON.image2URL)
            val image3 = getBitmapFromUrl(requestManager, newsItemJSON.image3URL)

            NewsItem(
                id = newsItemJSON.id,
                image = image,
                title = newsItemJSON.title,
                abbreviatedText = newsItemJSON.abbreviatedText,
                date = newsItemJSON.date,
                fundName = newsItemJSON.fundName,
                address = newsItemJSON.address,
                phone = newsItemJSON.phone,
                image2 = image2,
                image3 = image3,
                fullText = newsItemJSON.fullText,
                isChildrenCategory = newsItemJSON.isChildrenCategory,
                isAdultsCategory = newsItemJSON.isAdultsCategory,
                isElderlyCategory = newsItemJSON.isElderlyCategory,
                isAnimalsCategory = newsItemJSON.isAnimalsCategory,
                isEventsCategory = newsItemJSON.isEventsCategory
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
