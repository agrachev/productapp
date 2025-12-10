package ru.agrachev.core.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val imageURL: String,
    @ColumnInfo(name = "is_in_favorites") val isInFavorites: Boolean? = false,
)
