package com.example.marketplace_sederhana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.marketplace_sederhana.R

class ProductDetailFragment : Fragment() {

    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var addToCartButton: Button
    private lateinit var productImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        productNameTextView = view.findViewById(R.id.product_name)
        productPriceTextView = view.findViewById(R.id.product_price)
        addToCartButton = view.findViewById(R.id.add_to_cart_button)
        productImageView = view.findViewById(R.id.product_image)

        val productName = arguments?.getString("product_name")
        val productPrice = arguments?.getDouble("product_price")

        productNameTextView.text = productName
        productPriceTextView.text = "$${productPrice}"

        // Set a sample image for the product
        productImageView.setImageResource(R.drawable.sample_image)

        addToCartButton.setOnClickListener {
            // Simulate adding to cart
            // In a real app, you would save the product to a cart list
            // Navigate to CartFragment (or update the cart view)
            val cartFragment = CartFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, cartFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }
}
