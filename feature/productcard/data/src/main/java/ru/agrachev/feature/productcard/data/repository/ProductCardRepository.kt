package ru.agrachev.feature.productcard.data.repository

import kotlinx.coroutines.flow.map
import ru.agrachev.core.data.persistence.dao.ProductDao
import ru.agrachev.core.data.persistence.mapper.toDomainModel
import ru.agrachev.core.domain.service.FavoriteStatusUpdater
import ru.agrachev.feature.productcard.domain.repository.SingleProductRepository
import javax.inject.Inject

internal class ProductCardRepository @Inject constructor(
    favoriteStatusUpdater: FavoriteStatusUpdater,
    private val productDao: ProductDao,
) : SingleProductRepository, FavoriteStatusUpdater by favoriteStatusUpdater {

    override fun getProductById(id: Int) =
        productDao.getProductById(id)
            .map { it.toDomainModel() }
}
