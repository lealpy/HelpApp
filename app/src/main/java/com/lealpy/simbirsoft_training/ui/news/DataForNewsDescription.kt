package com.lealpy.simbirsoft_training.ui.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataForNewsDescription (
    val title : String,
    val date : String,
    val fundName : String,
    val address: String,
    val phone : String,
    val image : Int,
    val image2 : Int,
    val image3 : Int,
    val fullText : String,
) : Parcelable