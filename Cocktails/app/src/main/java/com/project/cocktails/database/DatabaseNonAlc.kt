package com.project.cocktails.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.cocktails.domain.NonAlcohol

@Entity
data class DatabaseNonAlc(

    @PrimaryKey
    var idDrink: Int,
    var strDrink: String,
    var strDrinkThumb: String

)

fun List<DatabaseNonAlc>.asDomainModel(): List<NonAlcohol> {
    return map {
        NonAlcohol(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb

        )
    }
}