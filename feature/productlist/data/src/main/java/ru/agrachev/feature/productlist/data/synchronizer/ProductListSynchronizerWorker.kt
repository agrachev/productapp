package ru.agrachev.feature.productlist.data.synchronizer

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import ru.agrachev.core.data.persistence.di.IoDispatcher
import ru.agrachev.feature.productlist.domain.repository.ProductListLocalRepository
import ru.agrachev.feature.productlist.domain.repository.ProductListRemoteRepository

@HiltWorker
internal class ProductListSynchronizerWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val remoteRepository: ProductListRemoteRepository,
    private val localRepository: ProductListLocalRepository,
    @param:IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork() = withContext(dispatcher) {
        try {
            val products = remoteRepository.fetchProducts()
            localRepository.upsertProducts(products)
            Result.success()
        } catch (e: Exception) {
            ensureActive()
            Result.failure(
                Data.Builder()
                    .putString(EXCEPTION_MESSAGE_TAG, e.message)
                    .build()
            )
        }
    }
}

internal const val EXCEPTION_MESSAGE_TAG = "exception_message"
