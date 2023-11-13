package com.example.destinationapp.data

import com.example.destinationapp.R

data class Discount(
    val image: Int,
    val title: String,
    val price: String,
)

val dummyDiscount = listOf(
    Discount(R.drawable.image6, "Pulau Komodo", "Rp 180.000"),
    Discount(R.drawable.image9, "Pulau Sumba", "Rp 160.000"),
    Discount(R.drawable.image8, "Raja Ampat", "Rp 200.000"),
    Discount(R.drawable.image7, "Borobudur", "Rp 130.000"),
)

val dummyBestSellerDiscount = dummyDiscount.shuffled()