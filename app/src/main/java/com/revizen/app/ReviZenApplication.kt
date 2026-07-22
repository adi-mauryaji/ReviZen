package com.revizen.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Main application class, annotated with HiltAndroidApp to initialize Dependency Injection.
 * Implements WorkManager Configuration.Provider to enable Hilt injection in background workers.
 */
@HiltAndroidApp
class ReviZenApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
