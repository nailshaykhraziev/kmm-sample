package com.fs.testkmp.android

import android.app.Application
import com.fs.testkmp.android.di.appModule
import com.fs.testkmp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, networkModule)
        }
    }
}
