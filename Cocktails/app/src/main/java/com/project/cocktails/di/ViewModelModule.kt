package com.project.cocktails.di

import com.project.cocktails.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CocktailViewModel(get()) }
    viewModel { NonAlcoholicViewModel(get()) }
    viewModel { RandomViewModel(get()) }
    viewModel { CocktailDetailViewModel()}
    viewModel { NonAlcDetailViewModel() }
    viewModel { RandomDetailViewModel() }
}