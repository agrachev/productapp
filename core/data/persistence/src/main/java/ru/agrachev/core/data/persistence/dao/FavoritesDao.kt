package ru.agrachev.core.data.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ru.agrachev.core.data.persistence.entity.ProductWithRating

@Dao
interface FavoritesDao {

    @Transaction
    @Query("SELECT * FROM products WHERE is_in_favorites = 1")
    fun getFavoritesProducts(): PagingSource<Int, ProductWithRating>
}
