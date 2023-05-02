package com.vb.payments

import android.app.Application
import com.vb.payments.di.modules.cardModule
import com.vb.payments.di.modules.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(cardModule, databaseModule)
        }
    }
}
