package ru.agrachev.feature.productlist.data.repository

import ru.agrachev.core.data.persistence.dao.ProductDao
import ru.agrachev.core.data.persistence.mapper.toEntityModel
import ru.agrachev.core.domain.entity.Product
import ru.agrachev.feature.productlist.domain.repository.ProductListLocalRepository
import javax.inject.Inject

internal class ProductListRoomRepository @Inject constructor(
    private val productDao: ProductDao,
) : ProductListLocalRepository {

    override suspend fun upsertProducts(products: List<Product>) =
        productDao.upsertProductsWithRatings(
            products = products.map {
                it.toEntityModel()
            },
            ratings = products.map {
                it.rating.toEntityModel(it)
            },
        )
}
