package com.project.cocktails.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.cocktails.domain.NonAlcohol
import com.project.cocktails.domain.Random

@Entity
data class DatabaseRandom (

    @PrimaryKey
    var idDrink: Int,
    var strDrink: String,
    var strDrinkThumb: String
)

fun List< DatabaseRandom>.asDomainModel(): List<Random> {
    return map {
        Random(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb

        )
    }
}