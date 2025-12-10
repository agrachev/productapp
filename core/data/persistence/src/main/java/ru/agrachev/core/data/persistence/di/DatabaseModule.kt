package ru.agrachev.core.data.persistence.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.agrachev.core.data.persistence.ProductAppDatabase
import ru.agrachev.core.data.persistence.service.ProductFavoriteStatusUpdater
import ru.agrachev.core.domain.service.FavoriteStatusUpdater
import ru.agrachev.core.domain.usecase.MarkProductAsFavoriteUsecase
import javax.inject.Singleton

@Module(includes = [DatabaseModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    @IoDispatcher
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    fun provideMarkProductAsFavoriteUsecase(
        favoriteStatusUpdater: FavoriteStatusUpdater,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = MarkProductAsFavoriteUsecase(favoriteStatusUpdater, ioDispatcher)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductAppDatabase = Room
        .databaseBuilder(
            context,
            ProductAppDatabase::class.java, "product-database"
        )
        .fallbackToDestructiveMigration(
            dropAllTables = false,
        )
        .build()

    @Provides
    fun provideProductDao(database: ProductAppDatabase) = database.productDao()

    @Provides
    fun provideFavoritesDao(database: ProductAppDatabase) = database.favoritesDao()

    @Provides
    fun provideMarkAsFavoriteDao(database: ProductAppDatabase) = database.markAsFavoriteDao()

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Singleton
        @Binds
        fun provideProductFavoriteStatusUpdater(
            repository: ProductFavoriteStatusUpdater,
        ): FavoriteStatusUpdater
    }
}
