package com.example.marketplace_sederhana.fragments.productdetail

import androidx.lifecycle.ViewModel
import com.example.marketplace_sederhana.models.Product
import com.example.marketplace_sederhana.repository.ProductRepository

class ProductDetailViewModel : ViewModel() {

    fun addToCart(product: Product) {
        ProductRepository.addToCart(product)
    }
}