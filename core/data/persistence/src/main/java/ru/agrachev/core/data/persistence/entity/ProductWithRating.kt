package ru.agrachev.core.data.persistence.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithRating(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val rating: RatingEntity,
)
