package com.example.destinationapp.model

data class Destination (
    val id: Long,
    val image: Int,
    val title: String,
    val description: String,
    val location: String,
    val price: Int
)