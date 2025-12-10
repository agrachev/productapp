package ru.agrachev.feature.productcard.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.agrachev.core.data.persistence.di.IoDispatcher
import ru.agrachev.feature.productcard.data.repository.ProductCardRepository
import ru.agrachev.feature.productcard.domain.repository.SingleProductRepository
import ru.agrachev.feature.productcard.domain.usecase.GetSingleProductUsecase
import javax.inject.Singleton

@Module(includes = [ProductCardDataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
internal class ProductCardDataModule {

    @Provides
    fun provideSingleProductUsecase(
        singleProductRepository: SingleProductRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ) = GetSingleProductUsecase(singleProductRepository, ioDispatcher)

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Singleton
        @Binds
        fun provideSingleProductRepository(
            repository: ProductCardRepository,
        ): SingleProductRepository
    }
}
