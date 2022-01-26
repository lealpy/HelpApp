package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem
import com.lealpy.simbirsoft_training.presentation.model.HelpItemUi
import com.lealpy.simbirsoft_training.presentation.model.NewsDescriptionItemUi
import com.lealpy.simbirsoft_training.presentation.model.NewsPreviewItemUi
import javax.inject.Inject

class PresentationUtils @Inject constructor(
    val appContext: Context,
    private val requestManager: RequestManager
) {

    companion object {
        const val LOG_TAG = "HelpAppLog"
    }

    fun getBitmapFromUrl(url : String) : Bitmap {
        return requestManager
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.no_photo)
            .error(R.drawable.no_photo)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .submit()
            .get()
    }

    fun getBitmapFromUri(uri : Uri) : Bitmap {
        val inputStream = appContext.contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    fun getBitmapById(id : Int) : Bitmap? {
        return appContext.getDrawable(id)?.toBitmap()
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

    fun getString(@StringRes id: Int): String {
        return appContext.getString(id)
    }

    fun showToast(text : String) {
        Toast.makeText(appContext, text, Toast.LENGTH_SHORT).show()
    }

    fun helpItemsToHelpItemsUi(helpItems : List<HelpItem>) : List<HelpItemUi> {
        return helpItems.map { helpItem ->
            val image = getBitmapFromUrl(helpItem.imageUrl)

            HelpItemUi(
                id = helpItem.id,
                image = image,
                text = helpItem.text
            )
        }
    }

    fun newsPreviewItemsToNewsPreviewItemsUi (
        newsPreviewItems: List<NewsPreviewItem>
    ) : List<NewsPreviewItemUi> {
        return newsPreviewItems.map { newsPreviewItem ->
            val image = getBitmapFromUrl(newsPreviewItem.imageUrl)
            NewsPreviewItemUi(
                id = newsPreviewItem.id,
                image = image,
                title = newsPreviewItem.title,
                abbreviatedText = newsPreviewItem.abbreviatedText,
                date = newsPreviewItem.date,
                isChildrenCategory = newsPreviewItem.isChildrenCategory,
                isAdultCategory = newsPreviewItem.isAdultCategory,
                isElderlyCategory = newsPreviewItem.isElderlyCategory,
                isAnimalCategory = newsPreviewItem.isAnimalCategory,
                isEventCategory = newsPreviewItem.isEventCategory
            )
        }
    }

    fun newsDescriptionItemToNewsDescriptionItemUi(
        newsDescriptionItem: NewsDescriptionItem
    ) : NewsDescriptionItemUi {
        val image = getBitmapFromUrl(newsDescriptionItem.imageUrl)
        val image2 = getBitmapFromUrl(newsDescriptionItem.image2Url)
        val image3 = getBitmapFromUrl(newsDescriptionItem.image3Url)

        return NewsDescriptionItemUi(
            id = newsDescriptionItem.id,
            image = image,
            title = newsDescriptionItem.title,
            abbreviatedText = newsDescriptionItem.abbreviatedText,
            date = newsDescriptionItem.date,
            fundName = newsDescriptionItem.fundName,
            address = newsDescriptionItem.address,
            phone = newsDescriptionItem.phone,
            image2 = image2,
            image3 = image3,
            fullText = newsDescriptionItem.fullText,
        )
    }

}
