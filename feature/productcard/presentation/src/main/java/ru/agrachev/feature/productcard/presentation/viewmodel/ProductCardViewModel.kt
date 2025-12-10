package ru.agrachev.feature.productcard.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.agrachev.core.domain.usecase.MarkProductAsFavoriteUsecase
import ru.agrachev.core.presentation.mapper.toUiModel
import ru.agrachev.core.presentation.viewmodel.ProductAppViewModel
import ru.agrachev.feature.productcard.domain.usecase.GetSingleProductUsecase
import ru.agrachev.feature.productcard.navigation.PRODUCT_ID_HANDLE
import ru.agrachev.feature.productcard.presentation.UiState
import javax.inject.Inject

@HiltViewModel
internal class ProductCardViewModel @Inject constructor(
    singleProductUsecase: GetSingleProductUsecase,
    markProductAsFavoriteUsecase: MarkProductAsFavoriteUsecase,
    savedStateHandle: SavedStateHandle,
) : ProductAppViewModel<UiState?>(markProductAsFavoriteUsecase) {

    override val itemFlow = (savedStateHandle.get<Int>(PRODUCT_ID_HANDLE)?.let { productId ->
        singleProductUsecase(productId)
    } ?: flow {
        throw Exception("$PRODUCT_ID_HANDLE handle is missing!")
    })
        .map { UiState.DataAvailable(it.toUiModel()) as UiState }
        .catch {
            emit(UiState.DataError(it) as UiState)
        }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = null,
        )
}
