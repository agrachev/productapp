package ru.agrachev.core.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.agrachev.core.data.persistence.dao.FavoritesDao
import ru.agrachev.core.data.persistence.dao.MarkAsFavoriteDao
import ru.agrachev.core.data.persistence.dao.ProductDao
import ru.agrachev.core.data.persistence.entity.ProductEntity
import ru.agrachev.core.data.persistence.entity.RatingEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        ProductEntity::class,
        RatingEntity::class,
    ],
)
internal abstract class ProductAppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun favoritesDao(): FavoritesDao

    abstract fun markAsFavoriteDao(): MarkAsFavoriteDao
}
