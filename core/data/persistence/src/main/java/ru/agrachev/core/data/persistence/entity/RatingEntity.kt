package ru.agrachev.core.data.persistence.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ratings",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class RatingEntity(
    @PrimaryKey val id: Int,
    val rate: Float,
    val count: Int,
)
