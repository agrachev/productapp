package ru.agrachev.feature.productlist.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ru.agrachev.core.data.persistence.entity.ProductWithRating
import ru.agrachev.feature.productlist.domain.ProductListSynchronizer
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class ProductWithRatingMediator @Inject constructor(
    private val synchronizer: ProductListSynchronizer,
) : RemoteMediator<Int, ProductWithRating>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductWithRating>,
    ): MediatorResult = when (loadType) {
        LoadType.REFRESH -> {
            try {
                synchronizer.requestSyncProducts()
                MediatorResult.Success(true)
            } catch (e: Exception) {
                MediatorResult.Error(e)
            }
        }

        LoadType.PREPEND -> MediatorResult.Success(true)
        LoadType.APPEND -> MediatorResult.Success(true)
    }
}
