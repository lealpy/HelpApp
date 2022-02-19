package com.lealpy.help_app.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.model.HelpItem
import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.presentation.model.HelpItemUi
import com.lealpy.help_app.presentation.model.NewsDescriptionItemUi
import com.lealpy.help_app.presentation.model.NewsPreviewItemUi
import com.lealpy.help_app.presentation.model.UserUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PresentationUtils @Inject constructor(
    val appContext: Context,
    private val requestManager: RequestManager,
) {

    fun getBitmapFromUrl(url : String) : Bitmap? {
        Log.e(LOG_TAG, Thread.currentThread().name.toString())
        return try {
            requestManager
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false)
                .submit()
                .get()
        } catch (e : Exception) {
            Log.e(LOG_TAG, e.message.toString())
            return getBitmapById(R.drawable.no_photo)
        }
    }

    fun getBitmapFromUri(uri : Uri) : Bitmap {
        val inputStream = appContext.contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    fun getBitmapById(id : Int) : Bitmap? {
        return AppCompatResources.getDrawable(appContext, id)?.toBitmap()
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

}
