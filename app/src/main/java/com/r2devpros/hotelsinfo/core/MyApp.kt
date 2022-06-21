package com.r2devpros.hotelsinfo.core

import android.app.Application
import com.r2devpros.hotelsinfo.BuildConfig
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("MyApp_TAG: onCreate: ")
    }
}