package ru.agrachev.feature.productcard.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.agrachev.core.domain.entity.Product

interface SingleProductRepository {

    fun getProductById(id: Int): Flow<Product>
}
