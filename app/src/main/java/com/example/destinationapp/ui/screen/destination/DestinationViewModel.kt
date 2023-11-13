package com.example.destinationapp.ui.screen.destination

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destinationapp.data.DestinationRepository
import com.example.destinationapp.model.Destination
import com.example.destinationapp.model.OrderDestination
import com.example.destinationapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DestinationViewModel(
    private val destination: DestinationRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderDestination>>> = MutableStateFlow(
        UiState.Loading)
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

    private val _groupedDestination = MutableStateFlow(
        destination.getAllDestinationSearch()
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )
    val groupedDestination: StateFlow<Map<Char, List<Destination>>> get() = _groupedDestination

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedDestination.value = destination.searchDestination(_query.value)
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    }
}