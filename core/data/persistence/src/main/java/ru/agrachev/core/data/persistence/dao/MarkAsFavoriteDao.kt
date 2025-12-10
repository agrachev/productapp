package ru.agrachev.core.data.persistence.dao

import androidx.room.Dao
import androidx.room.Update
import ru.agrachev.core.data.persistence.entity.FavoritePartialPayload
import ru.agrachev.core.data.persistence.entity.ProductEntity

@Dao
interface MarkAsFavoriteDao {

    @Update(entity = ProductEntity::class)
    suspend fun updateFavorite(favoritesPayload: FavoritePartialPayload)
}
