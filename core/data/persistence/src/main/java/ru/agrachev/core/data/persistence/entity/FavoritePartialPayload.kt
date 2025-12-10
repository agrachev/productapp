package ru.agrachev.core.data.persistence.entity

import androidx.room.ColumnInfo

data class FavoritePartialPayload(
    val id: Int,
    @ColumnInfo(name = "is_in_favorites") val isInFavorites: Boolean,
)
