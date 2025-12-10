package ru.agrachev.core.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiModel(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val imageURL: String,
    val rating: RatingUiModel,
    val isInFavorites: Boolean = false,
)
