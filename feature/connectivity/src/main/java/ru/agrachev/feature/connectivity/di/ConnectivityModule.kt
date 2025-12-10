package ru.agrachev.feature.connectivity.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.agrachev.feature.connectivity.data.repository.NetworkConnectivityRepository
import ru.agrachev.feature.connectivity.domain.repository.ConnectivityRepository
import javax.inject.Singleton

@Module(includes = [ConnectivityModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
internal class ConnectivityModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Singleton
        @Binds
        fun provideConnectivityRepository(
            repository: NetworkConnectivityRepository,
        ): ConnectivityRepository
    }
}
