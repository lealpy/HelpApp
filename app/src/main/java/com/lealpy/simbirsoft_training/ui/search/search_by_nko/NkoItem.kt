package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NkoItem (
    val id : Long,
    val name : String
) : Parcelable
