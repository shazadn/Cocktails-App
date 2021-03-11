package com.project.cocktails.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.project.cocktails.database.CocktailDatabase
import com.project.cocktails.database.asDomainModel
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.network.API_Calls
import com.project.cocktails.network.Drinks_APIServices
import com.project.cocktails.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CocktailRepository (
    private val drinksApiservices: Drinks_APIServices,
    private val database: CocktailDatabase
) {
    suspend fun refreshCocktails() {
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO) {
            Timber.d("Refresh Cocktails is called")
            val drink = drinksApiservices.getCocktail(API_Calls.Query_String_Cocktail).await()
            database.drinkDao.insertAll(drink.asDatabaseModel())
        }
    }
    val results: LiveData<List<Cocktail>> = Transformations.map(database.drinkDao.getLocalDBDrinks()){
        it.asDomainModel()
    }
}