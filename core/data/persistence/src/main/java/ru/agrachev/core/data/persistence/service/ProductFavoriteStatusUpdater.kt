package ru.agrachev.core.data.persistence.service

import ru.agrachev.core.data.persistence.dao.MarkAsFavoriteDao
import ru.agrachev.core.data.persistence.entity.FavoritePartialPayload
import ru.agrachev.core.domain.entity.Product
import ru.agrachev.core.domain.service.FavoriteStatusUpdater
import javax.inject.Inject

internal class ProductFavoriteStatusUpdater @Inject constructor(
    private val markAsFavoriteDao: MarkAsFavoriteDao,
) : FavoriteStatusUpdater {

    override suspend fun markAsFavorite(
        product: Product,
        isFavorite: Boolean,
    ) = markAsFavoriteDao.updateFavorite(
        FavoritePartialPayload(
            id = product.id,
            isInFavorites = isFavorite,
        )
    )
}
