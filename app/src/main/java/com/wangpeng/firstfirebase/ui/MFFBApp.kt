package com.wangpeng.firstfirebase.ui

import android.app.Application
import com.wangpeng.firstfirebase.BuildConfig
import com.wangpeng.firstfirebase.domain.di.domainModuleLists
import com.wangpeng.firstfirebase.domain.di.viewModelModules
import com.wangpeng.firstfirebase.network.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import timber.log.Timber

class MFFBApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MFFBApp)
            androidLogger()
            loadModules()
        }
    }

    private fun KoinApplication.loadModules() {
        modules(
            dataModule + domainModuleLists + viewModelModules
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
