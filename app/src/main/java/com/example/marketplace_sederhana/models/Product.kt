package com.example.marketplace_sederhana.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageResId: Int? = null,
    val imageUrl: String? = null
) : Parcelable