package com.hussein.composecodelab

import android.app.Application
import com.hussein.composecodelab.compose.room.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // Optional: Log Koin events
            androidContext(this@MyApplication)
            modules(databaseModule) // Add the database module
            // ... other modules
        }
    }
}