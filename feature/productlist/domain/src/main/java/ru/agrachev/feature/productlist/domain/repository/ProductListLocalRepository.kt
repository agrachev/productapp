package ru.agrachev.feature.productlist.domain.repository

import ru.agrachev.core.domain.entity.Product

interface ProductListLocalRepository {

    suspend fun upsertProducts(products: List<Product>)
}
