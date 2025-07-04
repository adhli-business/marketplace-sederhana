package com.example.marketplace_sederhana.models

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
