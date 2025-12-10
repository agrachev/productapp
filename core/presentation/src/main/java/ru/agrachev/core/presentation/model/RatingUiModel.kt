package ru.agrachev.core.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class RatingUiModel(
    val rate: Float,
    val count: Int,
)
