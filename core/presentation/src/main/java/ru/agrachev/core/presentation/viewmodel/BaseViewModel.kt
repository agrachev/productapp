package ru.agrachev.core.presentation.viewmodel

import kotlinx.coroutines.flow.Flow
import ru.agrachev.core.presentation.model.ProductUiModel

interface BaseViewModel<out T> {

    val itemFlow: Flow<T>

    fun toggleFavorite(product: ProductUiModel)
}
