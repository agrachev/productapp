package ru.agrachev.feature.productcard.presentation

import ru.agrachev.core.presentation.model.ProductUiModel

internal sealed interface UiState {

    class DataAvailable(val product: ProductUiModel) : UiState

    class DataError(val throwable: Throwable) : UiState
}
