package ru.agrachev.feature.productlist.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.agrachev.core.domain.entity.Product

interface ProductsRepository {

    fun getAllProducts(): Flow<PagingData<Product>>
}
