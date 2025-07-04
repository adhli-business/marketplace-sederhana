package com.example.marketplace_sederhana

// Renamed to avoid conflict with other Product class

data class MarketplaceProduct(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageResId: Int
)
