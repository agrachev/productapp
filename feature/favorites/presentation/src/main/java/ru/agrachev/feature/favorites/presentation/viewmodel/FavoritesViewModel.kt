package ru.agrachev.feature.favorites.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.agrachev.core.domain.usecase.MarkProductAsFavoriteUsecase
import ru.agrachev.core.presentation.mapper.toUiModel
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.viewmodel.ProductAppViewModel
import ru.agrachev.feature.favorites.domain.usecase.GetFavoritesUsecase
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    favoritesUsecase: GetFavoritesUsecase,
    markProductAsFavoriteUsecase: MarkProductAsFavoriteUsecase,
) : ProductAppViewModel<PagingData<ProductUiModel>>(markProductAsFavoriteUsecase) {

    override val itemFlow = favoritesUsecase()
        .map { pagingData ->
            pagingData.map { it.toUiModel() }
        }
        .flowOn(Dispatchers.Default)
        .cachedIn(
            scope = viewModelScope,
        )
}
