package com.fs.testkmp.android

import android.app.Application
import com.fs.testkmp.android.di.appModule
import com.fs.testkmp.di.dbModule
import com.fs.testkmp.di.filePathModule
import com.fs.testkmp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, networkModule, dbModule, filePathModule())
        }
    }
}
