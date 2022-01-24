package com.lealpy.simbirsoft_training.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HelpItem (
        val id: Long,
        val image: Bitmap,
        val text: String
) : Parcelable
