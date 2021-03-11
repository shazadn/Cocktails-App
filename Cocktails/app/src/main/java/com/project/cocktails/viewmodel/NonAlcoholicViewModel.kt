package com.project.cocktails.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.domain.NonAlcohol
import com.project.cocktails.repository.CocktailRepository
import com.project.cocktails.repository.Non_AlcoholicRepository
import kotlinx.coroutines.*

class NonAlcoholicViewModel (private val nonAlcoholicRepository: Non_AlcoholicRepository) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val nonAlcResults = nonAlcoholicRepository.results

    init {
        refreshFromRepository()
    }

    private fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                nonAlcoholicRepository.refreshNonAlcDrinks()
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
    private val _navigateSelectedProperty = MutableLiveData<NonAlcohol>()
    val navigateToSelectedProperty: LiveData<NonAlcohol>
        get() = _navigateSelectedProperty

    // When the drink is clicked
    fun displayPropertyDetails(nonAlcProperty: NonAlcohol) {
        _navigateSelectedProperty.value = nonAlcProperty
    }

    // After the navigation happens
    fun displayPropertyDetailsComplete() {
        _navigateSelectedProperty.value = null
    }
}