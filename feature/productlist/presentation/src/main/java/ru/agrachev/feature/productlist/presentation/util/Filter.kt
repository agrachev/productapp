package ru.agrachev.feature.productlist.presentation.util

import ru.agrachev.core.presentation.model.ProductUiModel

internal data class Filter(
    val title: String = "",
    val categories: Set<String> = emptySet(),
) {
    fun isProductMatches(product: ProductUiModel) =
        product.title.contains(title, true) &&
                product.category !in categories
}
