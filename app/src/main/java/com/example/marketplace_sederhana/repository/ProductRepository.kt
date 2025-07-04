package com.example.marketplace_sederhana.repository

import com.example.marketplace_sederhana.models.Product
import com.example.marketplace_sederhana.models.CartItem

object ProductRepository {
    private val cartItems = mutableListOf<CartItem>()

    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Smartphone XYZ", 2500000.0, "Smartphone dengan kamera 48MP dan RAM 8GB", imageUrl = "https://via.placeholder.com/300x300"),
            Product(2, "Laptop Gaming", 15000000.0, "Laptop gaming dengan GPU RTX 3060", imageUrl = "https://via.placeholder.com/300x300"),
            Product(3, "Headphone Wireless", 500000.0, "Headphone wireless dengan noise canceling", imageUrl = "https://via.placeholder.com/300x300"),
            Product(4, "Smartwatch", 1200000.0, "Smartwatch dengan monitor kesehatan", imageUrl = "https://via.placeholder.com/300x300"),
            Product(5, "Tablet 10 inch", 3000000.0, "Tablet 10 inch dengan stylus", imageUrl = "https://via.placeholder.com/300x300")
        )
    }

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            cartItems.add(CartItem(product))
        }
    }

    fun getCartItems(): List<CartItem> = cartItems.toList()

    fun getTotalPrice(): Double = cartItems.sumOf { it.product.price * it.quantity }
}