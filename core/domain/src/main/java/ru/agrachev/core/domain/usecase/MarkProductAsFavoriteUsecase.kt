package ru.agrachev.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.agrachev.core.domain.entity.Product
import ru.agrachev.core.domain.service.FavoriteStatusUpdater

class MarkProductAsFavoriteUsecase(
    private val favoriteStatusUpdater: FavoriteStatusUpdater,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(product: Product, isFavorite: Boolean) =
        withContext(ioDispatcher) {
            favoriteStatusUpdater.markAsFavorite(product, isFavorite)
        }
}
