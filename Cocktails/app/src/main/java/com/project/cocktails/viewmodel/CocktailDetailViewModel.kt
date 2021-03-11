package com.project.cocktails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.domain.Cocktail

class CocktailDetailViewModel: ViewModel() {
    private val _selectedProperty= MutableLiveData<Cocktail>()

    val selectedProperty: LiveData<Cocktail>
    get() = _selectedProperty

    fun setProperty(cocktailProperty: Cocktail) {
        _selectedProperty.value= cocktailProperty
    }
}