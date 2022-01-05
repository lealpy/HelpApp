package com.lealpy.simbirsoft_training.ui.news

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataForNewsDescription (
    val title : String,
    val date : String,
    val fundName : String,
    val address: String,
    val phone : String,
    val image : Bitmap,
    val image2 : Bitmap,
    val image3 : Bitmap,
    val fullText : String,
) : Parcelable