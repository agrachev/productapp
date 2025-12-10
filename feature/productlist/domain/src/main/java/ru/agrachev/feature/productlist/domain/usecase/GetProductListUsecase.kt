package ru.agrachev.feature.productlist.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.agrachev.feature.productlist.domain.repository.ProductsRepository

class GetProductListUsecase(
    private val productsRepository: ProductsRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke() = productsRepository
        .getAllProducts()
        .flowOn(ioDispatcher)
}
