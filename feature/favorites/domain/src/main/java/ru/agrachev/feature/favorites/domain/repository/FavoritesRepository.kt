package ru.agrachev.feature.favorites.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.agrachev.core.domain.entity.Product

interface FavoritesRepository {

    fun getFavorites(): Flow<PagingData<Product>>
}
