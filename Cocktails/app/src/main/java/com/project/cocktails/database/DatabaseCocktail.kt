package com.project.cocktails.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.cocktails.domain.Cocktail
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DatabaseCocktail(

    @PrimaryKey
    var idDrink: Int,
    var strDrink: String,
    var strDrinkThumb: String
) : Parcelable {}

fun List<DatabaseCocktail>.asDomainModel(): List<Cocktail> {
    return map {
        Cocktail(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}

