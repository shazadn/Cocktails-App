package com.project.cocktails.di

import android.app.Application
import androidx.room.Room
import com.project.cocktails.database.CocktailDatabase
import com.project.cocktails.database.NonAlcoholDao
import com.project.cocktails.database.NonAlcoholDatabase
import com.project.cocktails.database.RandomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun providesCocktailDatabase(application: Application): CocktailDatabase {
        return Room.databaseBuilder(application, CocktailDatabase::class.java, "cocktail.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun providesNonAlcoholDatabase(application: Application): NonAlcoholDatabase {
        return Room.databaseBuilder(application, NonAlcoholDatabase::class.java, "non-alcohol.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun providesRandomDatabase(application: Application): RandomDatabase {
        return Room.databaseBuilder(application, RandomDatabase::class.java, "random.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    // singleton: single instance
    single { providesCocktailDatabase(androidApplication()) } // lifecycle of the application
    single { providesNonAlcoholDatabase(androidApplication()) }
    single { providesRandomDatabase(androidApplication()) }

}