package com.example.marketplace_sederhana.fragments.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace_sederhana.models.Product
import com.example.marketplace_sederhana.repository.ProductRepository

class ProductListViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = ProductRepository.getProducts()
    }

    fun getProducts(): LiveData<List<Product>> {
        return _products
    }
}