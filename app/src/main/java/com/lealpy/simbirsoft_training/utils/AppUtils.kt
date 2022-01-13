package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.RequestManager

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

}