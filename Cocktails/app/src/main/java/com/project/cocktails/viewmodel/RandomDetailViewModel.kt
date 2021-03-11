package com.project.cocktails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.cocktails.domain.Random

class RandomDetailViewModel : ViewModel() {
    private val _selectedProperty = MutableLiveData<Random>()

    val selectedProperty: LiveData<Random>
        get() = _selectedProperty

    fun setProperty(randomProperty: Random) {
        _selectedProperty.value = randomProperty
    }
}