package com.example.destinationapp.data

import com.example.destinationapp.model.Destination
import com.example.destinationapp.model.FakeDestinationDataSource
import com.example.destinationapp.model.OrderDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DestinationRepository {

    private val orderDestinations = mutableListOf<OrderDestination>()

    init {
        if (orderDestinations.isEmpty()) {
            FakeDestinationDataSource.dummyDestinations.forEach {
                orderDestinations.add(OrderDestination(it, 0))
            }
        }
    }

    fun getAllDestinations(): Flow<List<OrderDestination>> {
        return flowOf(orderDestinations)
    }

    fun getAllDestinationSearch(): List<Destination> {
        return FakeDestinationDataSource.dummyDestinations
    }

    fun getOrderDestinationById(destinationId: Long): OrderDestination {
        return orderDestinations.first {
            it.destination.id == destinationId
        }
    }

    fun updateOrderDestination(destinationId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderDestinations.indexOfFirst { it.destination.id == destinationId }
        val result = if (index >= 0) {
            val orderDestination = orderDestinations[index]
            orderDestinations[index] =
                orderDestination.copy(destination = orderDestination.destination, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderDestinations(): Flow<List<OrderDestination>> {
        return getAllDestinations()
            .map { orderDestinations ->
                orderDestinations.filter { orderDestination ->
                    orderDestination.count != 0
                }
            }
    }

    fun searchDestination(query: String): List<Destination>{
        return FakeDestinationDataSource.dummyDestinations.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: DestinationRepository? = null

        fun getInstance(): DestinationRepository =
            instance ?: synchronized(this) {
                DestinationRepository().apply {
                    instance = this
                }
            }
    }
}