package ru.agrachev.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.agrachev.core.domain.usecase.MarkProductAsFavoriteUsecase
import ru.agrachev.core.presentation.mapper.toDomainModel
import ru.agrachev.core.presentation.model.ProductUiModel

abstract class ProductAppViewModel<out T>(
    private val markProductAsFavoriteUsecase: MarkProductAsFavoriteUsecase,
) : ViewModel(), BaseViewModel<T> {

    override fun toggleFavorite(product: ProductUiModel) {
        viewModelScope.launch {
            markProductAsFavoriteUsecase(product.toDomainModel(), !product.isInFavorites)
        }
    }
}
