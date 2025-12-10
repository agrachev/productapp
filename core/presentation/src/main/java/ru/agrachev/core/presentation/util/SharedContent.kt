package ru.agrachev.core.presentation.util

import ru.agrachev.core.presentation.model.ProductUiModel

enum class SharedContent(
    private val tag: String,
) {
    Image("image"),
    Title("title"),
    Price("price"),
    FavoriteButton("btnFav");

    fun key(model: ProductUiModel) = "$tag-${model.id}"
}
