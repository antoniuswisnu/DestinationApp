package com.example.destinationapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.destinationapp.R

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    R.drawable.jogja to R.string.category_jogja,
    R.drawable.surabaya to R.string.category_surabaya,
    R.drawable.bali to R.string.category_bali,
    R.drawable.lombok to R.string.category_lombok,
    R.drawable.malang to R.string.category_malang,
    R.drawable.jakarta to R.string.category_jakarta,
    R.drawable.bandung to R.string.category_bandung,
    R.drawable.semarang to R.string.category_semarang,
).map { Category(it.first, it.second) }