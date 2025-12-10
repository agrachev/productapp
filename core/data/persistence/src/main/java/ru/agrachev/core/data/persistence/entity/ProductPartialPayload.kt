package ru.agrachev.core.data.persistence.entity

data class ProductPartialPayload(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val imageURL: String,
)
