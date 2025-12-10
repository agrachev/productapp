package ru.agrachev.productapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
class ProductApplication : Application(), Configuration.Provider {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    internal interface HiltWorkerFactoryEntryPoint {
        val workerFactory: HiltWorkerFactory
    }

    override val workManagerConfiguration = Configuration.Builder()
        .setWorkerFactory(
            EntryPoints.get(
                this,
                HiltWorkerFactoryEntryPoint::class.java
            ).workerFactory
        )
        .build()
}
