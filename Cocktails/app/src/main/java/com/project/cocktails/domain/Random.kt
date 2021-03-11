package com.project.cocktails.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Random (
    var idDrink: Int,
    var strDrink: String,
    var strDrinkThumb: String
): Parcelable {}