package ru.agrachev.feature.connectivity.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.agrachev.feature.connectivity.domain.entity.ConnectionStatus

interface ConnectivityRepository {

    fun getConnectionStatuses(): Flow<ConnectionStatus>

    fun isConnected(): Boolean
}
