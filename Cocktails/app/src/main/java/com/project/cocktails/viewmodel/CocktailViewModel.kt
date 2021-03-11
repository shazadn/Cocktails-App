package com.project.cocktails.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.database.DatabaseCocktail
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.repository.CocktailRepository
import kotlinx.coroutines.*

class CocktailViewModel(private val cocktailRepository: CocktailRepository) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val cocktailResults = cocktailRepository.results

    init {
        refreshFromRepository()
    }

    private fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                cocktailRepository.refreshCocktails()
            } catch (networkError: Exception) {
                Log.e("Drinks error", networkError.localizedMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    // Livedata to handle the navigation to the selected property
    private val _navigateSelectedProperty = MutableLiveData<Cocktail>()
    val navigateToSelectedProperty: LiveData<Cocktail>
        get() = _navigateSelectedProperty

    // When the drink is clicked
    fun displayPropertyDetails(cocktailProperty: Cocktail) {
        _navigateSelectedProperty.value = cocktailProperty
    }

    // After the navigation happens
    fun displayPropertyDetailsComplete() {
        _navigateSelectedProperty.value = null
    }

}