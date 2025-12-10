package ru.agrachev.feature.productlist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.agrachev.core.domain.usecase.MarkProductAsFavoriteUsecase
import ru.agrachev.core.presentation.mapper.toUiModel
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.viewmodel.ProductAppViewModel
import ru.agrachev.feature.productlist.domain.usecase.GetProductListUsecase
import ru.agrachev.feature.productlist.presentation.util.Filter
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    productListUsecase: GetProductListUsecase,
    markProductAsFavoriteUsecase: MarkProductAsFavoriteUsecase,
) : ProductAppViewModel<PagingData<ProductUiModel>>(markProductAsFavoriteUsecase) {

    private val _filter = MutableStateFlow(Filter())

    override val itemFlow = productListUsecase()
        .map { pagingData ->
            pagingData.map { it.toUiModel() }
        }
        .cachedIn(
            scope = viewModelScope,
        )
        .combine(_filter.asStateFlow()) { list, filter ->
            list.filter { filter.isProductMatches(it) }
        }
        .flowOn(Dispatchers.Default)
        .cachedIn(
            scope = viewModelScope,
        )

    fun filter(title: String) = _filter.update {
        it.copy(
            title = title,
        )
    }

    fun filter(categories: Set<String>) = _filter.update {
        it.copy(
            categories = categories,
        )
    }
}
