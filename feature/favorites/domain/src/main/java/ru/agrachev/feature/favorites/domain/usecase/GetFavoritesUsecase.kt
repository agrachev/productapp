package ru.agrachev.feature.favorites.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.agrachev.feature.favorites.domain.repository.FavoritesRepository

class GetFavoritesUsecase(
    private val favoritesRepository: FavoritesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke() = favoritesRepository
        .getFavorites()
        .flowOn(ioDispatcher)
}
