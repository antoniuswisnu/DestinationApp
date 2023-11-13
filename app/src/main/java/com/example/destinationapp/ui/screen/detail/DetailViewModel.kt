package com.example.destinationapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destinationapp.data.DestinationRepository
import com.example.destinationapp.model.Destination
import com.example.destinationapp.model.OrderDestination
import com.example.destinationapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DestinationRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderDestination>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderDestination>>
        get() = _uiState

    fun getDestinationById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderDestinationById(rewardId))
        }
    }

    fun addToCart(reward: Destination, count: Int) {
        viewModelScope.launch {
            repository.updateOrderDestination(reward.id, count)
        }
    }
}