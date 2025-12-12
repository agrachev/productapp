package ru.agrachev.feature.productlist.data.synchronizer

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.withTimeout
import ru.agrachev.feature.productlist.domain.ProductListSynchronizer
import java.io.SyncFailedException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class WorkManagerProductListSynchronizer @Inject constructor(
    private val workManager: WorkManager,
) : ProductListSynchronizer {

    override suspend fun requestSyncProducts() {
        val syncRequest = OneTimeWorkRequestBuilder<ProductListSynchronizerWorker>()
            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
            .build()
        workManager.enqueueUniqueWork(
            UniqueWorkName,
            ExistingWorkPolicy.KEEP,
            syncRequest,
        )
        withTimeout(CONNECTION_TIMEOUT) {
            workManager.getWorkInfosForUniqueWorkFlow(UniqueWorkName)
                .map { it.firstOrNull() }
                .takeWhile { it?.state != WorkInfo.State.SUCCEEDED }
                .collect {
                    if (it?.state == WorkInfo.State.FAILED) {
                        throw SyncFailedException(it.outputData.getString(EXCEPTION_MESSAGE_TAG))
                    }
                }
        }
    }
}

private val UniqueWorkName = ProductListSynchronizerWorker::class.java.name
private val CONNECTION_TIMEOUT = 30.seconds
