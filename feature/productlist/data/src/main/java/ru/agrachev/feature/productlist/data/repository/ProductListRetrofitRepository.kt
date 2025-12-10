package ru.agrachev.feature.productlist.data.repository

import ru.agrachev.core.data.remote.mapper.toDomainModel
import ru.agrachev.feature.productlist.data.api.ProductsApi
import ru.agrachev.feature.productlist.domain.repository.ProductListRemoteRepository
import javax.inject.Inject

internal class ProductListRetrofitRepository @Inject constructor(
    private val productsApi: ProductsApi,
) : ProductListRemoteRepository {

    override suspend fun fetchProducts() =
        productsApi.getProducts()
            .map { it.toDomainModel() }
}
