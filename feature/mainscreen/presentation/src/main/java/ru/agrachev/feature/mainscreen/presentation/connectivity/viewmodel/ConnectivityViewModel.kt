package ru.agrachev.feature.mainscreen.presentation.connectivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.agrachev.feature.connectivity.domain.entity.ConnectionStatus
import ru.agrachev.feature.connectivity.domain.repository.ConnectivityRepository
import javax.inject.Inject

@HiltViewModel
internal class ConnectivityViewModel @Inject constructor(
    connectivityRepository: ConnectivityRepository
) : ViewModel() {

    val connectionStatusFlow = connectivityRepository.getConnectionStatuses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ConnectionStatus(connectivityRepository.isConnected()),
        )
}
