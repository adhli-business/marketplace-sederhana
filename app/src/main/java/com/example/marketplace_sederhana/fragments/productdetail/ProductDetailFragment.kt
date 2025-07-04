package com.example.marketplace_sederhana.fragments.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.marketplace_sederhana.MainActivity
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.fragments.cart.CartViewModel
import com.example.marketplace_sederhana.fragments.productlist.ProductListFragment
import com.example.marketplace_sederhana.models.Product
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.*

class ProductDetailFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var product: Product
    private lateinit var toolbar: Toolbar

    companion object {
        private const val ARG_PRODUCT = "product"

        fun newInstance(product: Product): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PRODUCT, product)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        product = arguments?.getParcelable(ARG_PRODUCT)!!

        setupToolbar(view)
        setupViews(view)
    }

    private fun setupToolbar(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Detail Produk"
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupViews(view: View) {
        val ivProduct = view.findViewById<ImageView>(R.id.iv_product)
        val tvName = view.findViewById<TextView>(R.id.tv_product_name)
        val tvPrice = view.findViewById<TextView>(R.id.tv_product_price)
        val tvDescription = view.findViewById<TextView>(R.id.tv_product_description)
        val btnAddToCart = view.findViewById<Button>(R.id.btn_add_to_cart)

        tvName.text = product.name
        tvPrice.text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(product.price)
        tvDescription.text = product.description

        // Load product image
        product.imageUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.ic_cart_white)
                .into(ivProduct)
        }

        btnAddToCart.setOnClickListener {
            cartViewModel.addToCart(product)
            showSuccessMessage()
            // Navigate back to product list
            (activity as MainActivity).loadFragment(ProductListFragment())
        }
    }

    private fun showSuccessMessage() {
        Snackbar.make(
            requireView(),
            "${product.name} berhasil ditambahkan ke keranjang",
            Snackbar.LENGTH_SHORT
        ).apply {
            setBackgroundTint(resources.getColor(R.color.accent))
            setTextColor(resources.getColor(R.color.white))
            show()
        }
    }
}