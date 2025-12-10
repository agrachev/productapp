package ru.agrachev.feature.favorites.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.agrachev.core.data.persistence.dao.FavoritesDao
import ru.agrachev.core.data.persistence.mapper.toDomainModel
import ru.agrachev.core.domain.service.FavoriteStatusUpdater
import ru.agrachev.feature.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

internal class FavoritesListRepository @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    favoriteStatusUpdater: FavoriteStatusUpdater,
    private val favoritesDao: FavoritesDao,
) : FavoritesRepository, FavoriteStatusUpdater by favoriteStatusUpdater {

    @OptIn(ExperimentalPagingApi::class)
    private val favoritesFlow by lazy(LazyThreadSafetyMode.NONE) {
        Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
        ) {
            favoritesDao.getFavoritesProducts()
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
    }

    override fun getFavorites() = favoritesFlow
}
