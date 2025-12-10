package ru.agrachev.feature.productlist.data.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import androidx.work.WorkManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import ru.agrachev.core.data.persistence.di.IoDispatcher
import ru.agrachev.core.data.persistence.entity.ProductWithRating
import ru.agrachev.feature.productlist.data.ProductWithRatingMediator
import ru.agrachev.feature.productlist.data.api.ProductsApi
import ru.agrachev.feature.productlist.data.repository.ProductListRepository
import ru.agrachev.feature.productlist.data.repository.ProductListRetrofitRepository
import ru.agrachev.feature.productlist.data.repository.ProductListRoomRepository
import ru.agrachev.feature.productlist.data.synchronizer.WorkManagerProductListSynchronizer
import ru.agrachev.feature.productlist.domain.ProductListSynchronizer
import ru.agrachev.feature.productlist.domain.repository.ProductListLocalRepository
import ru.agrachev.feature.productlist.domain.repository.ProductListRemoteRepository
import ru.agrachev.feature.productlist.domain.repository.ProductsRepository
import ru.agrachev.feature.productlist.domain.usecase.GetProductListUsecase
import javax.inject.Singleton

@Module(includes = [ProductListDataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
internal class ProductListDataModule {

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context) = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)

    @Provides
    fun provideProductListUsecase(
        productsRepository: ProductsRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = GetProductListUsecase(productsRepository, ioDispatcher)

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Singleton
        @Binds
        fun provideProductsRepository(
            repository: ProductListRepository,
        ): ProductsRepository

        @Singleton
        @Binds
        fun provideLocalRepository(
            repository: ProductListRoomRepository,
        ): ProductListLocalRepository

        @Singleton
        @Binds
        fun provideRemoteRepository(
            repository: ProductListRetrofitRepository,
        ): ProductListRemoteRepository

        @Singleton
        @Binds
        fun provideSynchronizer(
            synchronizer: WorkManagerProductListSynchronizer,
        ): ProductListSynchronizer

        @OptIn(ExperimentalPagingApi::class)
        @Binds
        fun providePagingMediator(
            productWithRatingMediator: ProductWithRatingMediator,
        ): RemoteMediator<Int, ProductWithRating>
    }
}
