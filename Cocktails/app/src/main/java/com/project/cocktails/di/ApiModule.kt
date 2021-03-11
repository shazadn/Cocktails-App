package com.project.cocktails.di

import com.project.cocktails.network.Drinks_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): Drinks_APIServices{
        return retrofit.create(Drinks_APIServices::class.java)
    }

    single { provideUserApi(get()) }
}