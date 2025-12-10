package ru.agrachev.feature.favorites.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.agrachev.core.data.persistence.di.IoDispatcher
import ru.agrachev.feature.favorites.data.repository.FavoritesListRepository
import ru.agrachev.feature.favorites.domain.repository.FavoritesRepository
import ru.agrachev.feature.favorites.domain.usecase.GetFavoritesUsecase
import javax.inject.Singleton

@Module(includes = [FavoritesDataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
internal class FavoritesDataModule {

    @Provides
    fun provideFavoritesUsecase(
        favoritesRepository: FavoritesRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = GetFavoritesUsecase(favoritesRepository, ioDispatcher)

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Singleton
        @Binds
        fun provideFavoritesRepository(
            repository: FavoritesListRepository,
        ): FavoritesRepository
    }
}
