package ru.agrachev.core.domain.entity

data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val imageURL: String,
    val rating: Rating,
    val isInFavorites: Boolean = false,
)
