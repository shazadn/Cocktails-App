package com.project.cocktails.di

import com.project.cocktails.database.CocktailDatabase
import com.project.cocktails.database.NonAlcoholDao
import com.project.cocktails.database.NonAlcoholDatabase
import com.project.cocktails.database.RandomDatabase
import com.project.cocktails.network.Drinks_APIServices
import com.project.cocktails.repository.CocktailRepository
import com.project.cocktails.repository.Non_AlcoholicRepository
import com.project.cocktails.repository.RandomRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideCocktailRepository(api: Drinks_APIServices, dao:CocktailDatabase): CocktailRepository {
        return CocktailRepository(api, dao)
    }

    fun provideNonAlcoholRepository(api: Drinks_APIServices, dao: NonAlcoholDatabase): Non_AlcoholicRepository {
        return Non_AlcoholicRepository(api, dao)
    }

    fun provideRandomRepository(api: Drinks_APIServices, dao: RandomDatabase):RandomRepository {
        return RandomRepository(api, dao)
    }

    single { provideCocktailRepository(get(), get()) }
    single { provideNonAlcoholRepository(get(), get()) }
    single { provideRandomRepository(get(), get()) }
}