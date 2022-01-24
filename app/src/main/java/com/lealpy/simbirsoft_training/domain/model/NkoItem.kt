package com.lealpy.simbirsoft_training.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NkoItem (
    val id : Long,
    val name : String
) : Parcelable
