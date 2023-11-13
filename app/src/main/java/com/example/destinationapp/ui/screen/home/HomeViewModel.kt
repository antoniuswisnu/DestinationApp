package com.example.destinationapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destinationapp.data.DestinationRepository
import com.example.destinationapp.model.OrderDestination
import com.example.destinationapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val destination: DestinationRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderDestination>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderDestination>>>
        get() = _uiState

    fun getAllDestinations() {
        viewModelScope.launch {
            destination.getAllDestinations()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderDestinations ->
                    _uiState.value = UiState.Success(orderDestinations)
                }
        }
    }
}