package com.candra.trainingkopdig.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val name: String,
    val description: String,
    val cost: String,
    val price: String,
    val photo: String,
    val growing_area: String
): Parcelable