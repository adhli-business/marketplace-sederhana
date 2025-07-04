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

class ProductListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductItemAdapter

    // Dummy data for products
    private val products = listOf(
        MarketplaceProduct(1, "Product 1", 100.0, "Deskripsi produk 1", 0),
        MarketplaceProduct(2, "Product 2", 150.0, "Deskripsi produk 2", 0),
        MarketplaceProduct(3, "Product 3", 200.0, "Deskripsi produk 3", 0),
        MarketplaceProduct(4, "Product 4", 50.0, "Deskripsi produk 4", 0),
        MarketplaceProduct(5, "Product 5", 80.0, "Deskripsi produk 5", 0)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.product_list_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ProductItemAdapter(products) { product ->
            // Handle item click (navigate to ProductDetailFragment)
            val detailFragment = ProductDetailFragment()
            val bundle = Bundle()
            bundle.putString("product_name", product.name)
            bundle.putDouble("product_price", product.price)
            detailFragment.arguments = bundle

            // Navigate to ProductDetailFragment
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, detailFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        recyclerView.adapter = adapter

        return view
    }
}
