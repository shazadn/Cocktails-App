package com.project.cocktails.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.domain.Cocktail
import com.project.cocktails.domain.Random
import com.project.cocktails.repository.RandomRepository
import kotlinx.coroutines.*

class RandomViewModel (private val randomRepository: RandomRepository) : ViewModel(){
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    val randomResults = randomRepository.results

    init {
        refreshFromRepository()
    }

    private fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                randomRepository.refreshRandomDrinks()
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
    private val _navigateSelectedProperty = MutableLiveData<Random>()
    val navigateToSelectedProperty: LiveData<Random>
        get() = _navigateSelectedProperty

    // When the drink is clicked
    fun displayPropertyDetails(randomProperty: Random) {
        _navigateSelectedProperty.value = randomProperty
    }

    // After the navigation happens
    fun displayPropertyDetailsComplete() {
        _navigateSelectedProperty.value = null
    }
}