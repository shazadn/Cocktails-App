package com.project.cocktails.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Drinks_APIServices {
    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
    @GET(API_Calls.API_DRINKS_LIST)
    fun getCocktail(@Query("c") queryString: String): Deferred<NetworkCocktailContainer>

    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
    @GET(API_Calls.API_DRINKS_LIST)
    fun getNoAlcohol(@Query("a") queryString: String): Deferred<NetworkNonAlcContainer>

//    @GET(API_Calls.API_RANDOM_DRINK)
//    fun getRandom(): Deferred<NetworkRandomContainer>

    @GET(API_Calls.API_MARGARITA)
    fun getRandom(@Query("s") queryString: String): Deferred<NetworkRandomContainer>
}