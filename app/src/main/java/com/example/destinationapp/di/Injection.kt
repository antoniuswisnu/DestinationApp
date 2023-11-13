package com.example.destinationapp.di

import com.example.destinationapp.data.DestinationRepository

object Injection {
    fun provideRepository(): DestinationRepository {
        return DestinationRepository.getInstance()
    }
}