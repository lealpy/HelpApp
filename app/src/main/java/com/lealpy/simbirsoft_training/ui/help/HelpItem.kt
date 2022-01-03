package com.lealpy.simbirsoft_training.ui.help

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HelpItem (
        val id: Long,
        val image: Int,
        val text: String
) : Parcelable
