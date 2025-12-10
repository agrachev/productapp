package ru.agrachev.feature.productlist.domain.repository

import ru.agrachev.core.domain.entity.Product

interface ProductListRemoteRepository {

    suspend fun fetchProducts(): List<Product>
}
