package ru.agrachev.feature.connectivity.data.repository

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import ru.agrachev.feature.connectivity.domain.entity.ConnectionStatus
import ru.agrachev.feature.connectivity.domain.repository.ConnectivityRepository
import javax.inject.Inject

internal class NetworkConnectivityRepository @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) : ConnectivityRepository {

    override fun getConnectionStatuses() = callbackFlow {
        val networkRequest = NetworkRequest.Builder().run {
            TransportTypes.forEach { addTransportType(it) }
            NetworkCapabilities.forEach { addCapability(it) }
            build()
        }
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                emitConnectionStatus(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                emitConnectionStatus(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                emitConnectionStatus(false)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val isConnected = networkCapabilities.hasCapability(NET_CAPABILITY_VALIDATED)
                emitConnectionStatus(isConnected)
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    override fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork)
        return when {
            activeNetwork == null || networkCapabilities == null -> false
            else -> TransportTypes.any { networkCapabilities.hasTransport(it) }
        }
    }
}

private val TransportTypes = arrayOf(TRANSPORT_WIFI, TRANSPORT_CELLULAR, TRANSPORT_ETHERNET)
private val NetworkCapabilities = arrayOf(NET_CAPABILITY_INTERNET)

private fun ProducerScope<ConnectionStatus>.emitConnectionStatus(isConnected: Boolean) =
    trySend(ConnectionStatus(isConnected))
