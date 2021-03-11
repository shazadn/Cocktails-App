package com.project.cocktails.network

import com.project.cocktails.database.DatabaseCocktail
import com.project.cocktails.database.DatabaseNonAlc
import com.project.cocktails.database.DatabaseRandom
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//COCKTAIL - container
@JsonClass(generateAdapter = true)
data class NetworkCocktailContainer(val drinks: List<ValueCocktailItem>)

data class ValueCocktailItem(

    @Json(name = "idDrink") var idDrink: Int,
    @Json(name = "strDrink") var strDrink: String,
    @Json(name = "strThumb") var strDrinkThumb: String
    )

fun NetworkCocktailContainer.asDatabaseModel(): List<DatabaseCocktail> {
    return drinks.map{
        DatabaseCocktail(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}

//NON ALCOHOL - container
@JsonClass(generateAdapter = true)
data class NetworkNonAlcContainer(val drinks: List<ValueNonAlcItem>)

data class ValueNonAlcItem(

    @Json(name = "idDrink") var idDrink: Int,
    @Json(name = "strDrink") var strDrink: String,
    @Json(name = "strThumb") var strDrinkThumb: String
)

fun NetworkNonAlcContainer.asDatabaseModel(): List<DatabaseNonAlc> {
    return drinks.map{
        DatabaseNonAlc(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}

//RANDOM - container
@JsonClass(generateAdapter = true)
data class NetworkRandomContainer(val drinks: List<ValueRandomItem>)

data class ValueRandomItem(

    @Json(name = "idDrink") var idDrink: Int,
    @Json(name = "strDrink") var strDrink: String,
    @Json(name = "strThumb") var strDrinkThumb: String
)

fun NetworkRandomContainer.asDatabaseModel(): List<DatabaseRandom> {
    return drinks.map{
        DatabaseRandom(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}

