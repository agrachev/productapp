package ru.agrachev.core.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import ru.agrachev.core.presentation.OnFavoritesClickedCallback
import ru.agrachev.core.presentation.OnProductCardClickedCallback
import ru.agrachev.core.presentation.component.CenteredProgressIndicator
import ru.agrachev.core.presentation.component.NoDataAlert
import ru.agrachev.core.presentation.component.PagingDataLoadingErrorItem
import ru.agrachev.core.presentation.component.ProductCard
import ru.agrachev.core.presentation.component.RemoteDataLoadingErrorAlert
import ru.agrachev.core.presentation.model.ProductUiModel
import ru.agrachev.core.presentation.util.TrailingItem
import ru.agrachev.core.presentation.util.UiState
import ru.agrachev.core.presentation.util.rememberUiState
import ru.agrachev.core.presentation.viewmodel.ProductAppViewModel

@Composable
fun ProductListScreenTemplate(
    onProductCardClicked: OnProductCardClickedCallback,
    viewModel: ProductAppViewModel<PagingData<ProductUiModel>>,
    extraContent: @Composable ((LazyPagingItems<ProductUiModel>) -> Unit)? = null,
) {
    val itemList = viewModel.itemFlow.collectAsLazyPagingItems()
    val onFavoritesClicked: OnFavoritesClickedCallback = remember {
        {
            viewModel.toggleFavorite(it)
        }
    }
    val uiState by rememberUiState(itemList)
    when (uiState) {
        is UiState.SourceDataLoading -> CenteredProgressIndicator(
            modifier = Modifier
                .fillMaxSize(),
        )

        is UiState.RemoteDataLoadingError -> RemoteDataLoadingErrorAlert(
            productList = itemList,
            throwable = (uiState as UiState.RemoteDataLoadingError).throwable,
            modifier = Modifier
                .fillMaxSize(),
        )

        is UiState.SourceDataAvailable -> {
            val trailingItem = (uiState as UiState.SourceDataAvailable).trailingItem
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                extraContent?.invoke(itemList)
                if (itemList.itemCount > 0) {
                    ProductList(
                        trailingItem = trailingItem,
                        onFavoritesClicked = onFavoritesClicked,
                        onItemClicked = onProductCardClicked,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        itemList
                    }
                } else {
                    NoDataAlert(
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private inline fun ProductList(
    noinline onFavoritesClicked: OnFavoritesClickedCallback,
    noinline onItemClicked: OnProductCardClickedCallback,
    trailingItem: TrailingItem?,
    modifier: Modifier = Modifier,
    itemListProvider: () -> LazyPagingItems<ProductUiModel>,
) {
    itemListProvider().also { itemList ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
            modifier = modifier,
        ) {
            items(
                count = itemList.itemCount,
                key = itemList.itemKey { it.id },
            ) { index ->
                itemList[index]?.let {
                    ProductCard(
                        model = it,
                        onFavoritesClicked = onFavoritesClicked,
                        onItemClicked = onItemClicked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 100.dp)
                            .animateItem(),
                    )
                }
            }
            trailingItem?.let {
                when (it) {
                    is TrailingItem.PageLoadingIndicator -> item(
                        contentType = { it },
                    ) {
                        CenteredProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }

                    is TrailingItem.PageLoadingErrorItem -> item(
                        contentType = { it }
                    ) {
                        PagingDataLoadingErrorItem(
                            productList = itemList,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}
