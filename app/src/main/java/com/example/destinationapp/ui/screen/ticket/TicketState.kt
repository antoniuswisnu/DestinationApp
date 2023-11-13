package com.example.destinationapp.ui.screen.ticket

import com.example.destinationapp.model.OrderDestination

data class TicketState(
    val orderDestination: List<OrderDestination>,
    val totalRequiredPoint: Int
)