package com.project.cocktails

import android.app.Application
import com.project.cocktails.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DrinkApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DrinkApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule)
        }
    }
}