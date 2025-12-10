package ru.agrachev.feature.productlist.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.agrachev.core.data.persistence.dao.ProductDao
import ru.agrachev.core.data.persistence.entity.ProductWithRating
import ru.agrachev.core.data.persistence.mapper.toDomainModel
import ru.agrachev.core.domain.service.FavoriteStatusUpdater
import ru.agrachev.feature.productlist.domain.repository.ProductsRepository
import javax.inject.Inject

internal class ProductListRepository @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    remoteMediator: RemoteMediator<Int, ProductWithRating>,
    favoriteStatusUpdater: FavoriteStatusUpdater,
    private val productDao: ProductDao,
) : ProductsRepository, FavoriteStatusUpdater by favoriteStatusUpdater {

    @OptIn(ExperimentalPagingApi::class)
    private val productsFlow by lazy(LazyThreadSafetyMode.NONE) {
        Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            remoteMediator = remoteMediator,
        ) {
            productDao.getAllProducts()
        }
            .flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
    }

    override fun getAllProducts() = productsFlow
}
