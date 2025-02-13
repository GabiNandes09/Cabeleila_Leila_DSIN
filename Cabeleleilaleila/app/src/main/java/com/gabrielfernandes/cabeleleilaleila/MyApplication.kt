package com.gabrielfernandes.cabeleleilaleila

import android.app.Application
import com.gabrielfernandes.cabeleleilaleila.modules.appModule
import com.gabrielfernandes.cabeleleilaleila.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, networkModule)
        }
    }
}