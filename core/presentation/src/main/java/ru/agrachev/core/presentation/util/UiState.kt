package ru.agrachev.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
internal fun <T : Any> rememberUiState(items: LazyPagingItems<T>) = remember(items) {
    derivedStateOf {
        val sourceRefreshState = items.loadState.source.refresh
        val remoteRefreshState = items.loadState.refresh
        when {
            anyOf<LoadState.Loading>(
                sourceRefreshState,
                remoteRefreshState
            ) && items.itemCount == 0 -> UiState.SourceDataLoading

            remoteRefreshState is LoadState.Error && items.itemCount == 0 -> UiState.RemoteDataLoadingError(
                remoteRefreshState.error
            )

            sourceRefreshState is LoadState.NotLoading || items.itemCount > 0 -> {
                val appendState = items.loadState.source.append
                UiState.SourceDataAvailable(
                    when (appendState) {
                        is LoadState.Loading -> TrailingItem.PageLoadingIndicator
                        is LoadState.Error -> TrailingItem.PageLoadingErrorItem(
                            appendState.error
                        )

                        else -> null
                    }
                )
            }

            else -> Unit
        }
    }
}

internal sealed interface UiState {

    object SourceDataLoading : UiState

    data class RemoteDataLoadingError(val throwable: Throwable) : UiState

    data class SourceDataAvailable(val trailingItem: TrailingItem?) : UiState
}

private inline fun <reified T : LoadState> anyOf(vararg states: LoadState) = states.any { it is T }
