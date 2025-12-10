package ru.agrachev.core.data.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.agrachev.core.data.persistence.entity.ProductEntity
import ru.agrachev.core.data.persistence.entity.ProductPartialPayload
import ru.agrachev.core.data.persistence.entity.ProductWithRating
import ru.agrachev.core.data.persistence.entity.RatingEntity

@Dao
abstract class ProductDao {

    @Transaction
    @Query("SELECT * FROM products")
    abstract fun getAllProducts(): PagingSource<Int, ProductWithRating>

    @Transaction
    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    abstract fun getProductById(id: Int): Flow<ProductWithRating>

    @Transaction
    open suspend fun upsertProductsWithRatings(
        products: List<ProductPartialPayload>,
        ratings: List<RatingEntity>,
    ) {
        upsertProducts(products)
        upsertRatings(ratings)
        deleteProductsExcept(products.map { it.id })
    }

    @Upsert(entity = ProductEntity::class)
    internal abstract suspend fun upsertProducts(products: List<ProductPartialPayload>)

    @Upsert
    internal abstract suspend fun upsertRatings(ratings: List<RatingEntity>)

    @Query("DELETE FROM products WHERE id NOT IN (:ids)")
    internal abstract suspend fun deleteProductsExcept(ids: List<Int>)
}
