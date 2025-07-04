package com.example.marketplace_sederhana.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace_sederhana.models.CartItem
import com.example.marketplace_sederhana.models.Product

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems

    fun addToCart(product: Product) {
        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = currentItems.find { it.product.id == product.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentItems.add(CartItem(product))
        }
        _cartItems.value = currentItems
    }

    fun removeFromCart(product: Product) {
        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        currentItems.removeAll { it.product.id == product.id }
        _cartItems.value = currentItems
    }

    fun updateQuantity(product: Product, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(product)
            return
        }

        val currentItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val item = currentItems.find { it.product.id == product.id }
        item?.quantity = quantity
        _cartItems.value = currentItems
    }

    fun getTotalPrice(): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
