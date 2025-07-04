package com.example.marketplace_sederhana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace_sederhana.ProductItemAdapter
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.MarketplaceProduct

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductItemAdapter

    // Dummy cart items
    private val cartItems = listOf(
        MarketplaceProduct(1, "Product 1", 100.0, "Dummy product 1", 0),
        MarketplaceProduct(2, "Product 2", 150.0, "Dummy product 2", 0)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.cart_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ProductItemAdapter(cartItems) { product ->
            // Handle item click in cart (e.g., remove from cart)
        }

        recyclerView.adapter = adapter

        return view
    }
}
