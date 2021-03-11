package com.project.cocktails.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.project.cocktails.database.NonAlcoholDao
import com.project.cocktails.database.NonAlcoholDatabase
import com.project.cocktails.database.asDomainModel
import com.project.cocktails.domain.NonAlcohol
import com.project.cocktails.network.API_Calls
import com.project.cocktails.network.Drinks_APIServices
import com.project.cocktails.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class Non_AlcoholicRepository(
    private val drinksApiservices: Drinks_APIServices,
    private val database: NonAlcoholDatabase
) {
    suspend fun refreshNonAlcDrinks() {
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO) {
            Timber.d("Refresh Non Alcoholic drinks is called")
            val drink = drinksApiservices.getNoAlcohol(API_Calls.Query_String_NonAlc).await()
            database.drinkDao.insertAll(drink.asDatabaseModel())
        }
    }

    val results: LiveData<List<NonAlcohol>> = Transformations.map(database.drinkDao.getLocalDBDrinks()) {
        it.asDomainModel()
    }
}