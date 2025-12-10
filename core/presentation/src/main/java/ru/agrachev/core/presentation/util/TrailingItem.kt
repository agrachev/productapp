package ru.agrachev.core.presentation.util

internal sealed interface TrailingItem {

    object PageLoadingIndicator : TrailingItem

    data class PageLoadingErrorItem(val throwable: Throwable) : TrailingItem
}
