package com.octagon_technologies.smart_calculator.presentation

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.octagon_technologies.smart_calculator.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val isDebug = BuildConfig.DEBUG
        if (isDebug) Timber.plant(Timber.DebugTree())
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!isDebug)
    }
}