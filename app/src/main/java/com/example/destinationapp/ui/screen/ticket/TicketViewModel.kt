package com.example.destinationapp.ui.screen.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destinationapp.data.DestinationRepository
import com.example.destinationapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel(
    private val repository: DestinationRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<TicketState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TicketState>>
        get() = _uiState

    fun getAddedOrderDestinations() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderDestinations()
                .collect { orderDestination ->
                    val totalRequiredPoint =
                        orderDestination.sumOf { it.destination.price * it.count }
                    _uiState.value = UiState.Success(TicketState(orderDestination, totalRequiredPoint))
                }
        }
    }

    fun updateOrderDestination(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderDestination(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderDestinations()
                    }
                }
        }
    }
}