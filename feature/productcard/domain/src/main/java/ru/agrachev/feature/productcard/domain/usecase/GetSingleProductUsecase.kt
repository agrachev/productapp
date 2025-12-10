package ru.agrachev.feature.productcard.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.agrachev.feature.productcard.domain.repository.SingleProductRepository

class GetSingleProductUsecase(
    private val singleProductRepository: SingleProductRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(id: Int) = singleProductRepository
        .getProductById(id)
        .flowOn(ioDispatcher)
}
