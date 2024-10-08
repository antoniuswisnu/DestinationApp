package com.example.destinationapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.destinationapp.data.DestinationRepository
import com.example.destinationapp.ui.screen.destination.DestinationViewModel
import com.example.destinationapp.ui.screen.detail.DetailViewModel
import com.example.destinationapp.ui.screen.ticket.TicketViewModel

class ViewModelFactory(private val repository: DestinationRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(TicketViewModel::class.java)) {
            return TicketViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            return DestinationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}