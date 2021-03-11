package com.project.cocktails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.domain.NonAlcohol

class NonAlcDetailViewModel : ViewModel() {
    private val _selectedProperty = MutableLiveData<NonAlcohol>()

    val selectedProperty: LiveData<NonAlcohol>
        get() = _selectedProperty

    fun setProperty(nonAlcProperty: NonAlcohol) {
        _selectedProperty.value = nonAlcProperty
    }
}
