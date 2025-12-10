package ru.agrachev.feature.productlist.data.api

import retrofit2.http.GET
import ru.agrachev.core.data.remote.dto.ProductDto

internal interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}
