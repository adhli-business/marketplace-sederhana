package com.example.marketplace_sederhana.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace_sederhana.R
import com.google.android.material.button.MaterialButton
import java.text.NumberFormat
import java.util.*

class CartFragment : Fragment() {
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter
    private lateinit var rvCart: RecyclerView
    private lateinit var tvEmptyCart: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnCheckout: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        // Initialize views
        rvCart = view.findViewById(R.id.rv_cart)
        tvEmptyCart = view.findViewById(R.id.tv_empty_cart)
        tvTotalPrice = view.findViewById(R.id.tv_total_price)
        btnCheckout = view.findViewById(R.id.btn_checkout)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            onQuantityChanged = { item, newQuantity ->
                viewModel.updateQuantity(item.product, newQuantity)
            },
            onItemRemoved = { item ->
                viewModel.removeFromCart(item.product)
                Toast.makeText(context, "${item.product.name} dihapus dari keranjang", Toast.LENGTH_SHORT).show()
            }
        )

        rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CartFragment.adapter
        }
    }

    private fun setupObservers() {
        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            updateEmptyState(items.isEmpty())
            updateTotalPrice()
        }
    }

    private fun setupClickListeners() {
        btnCheckout.setOnClickListener {
            if (viewModel.cartItems.value?.isEmpty() == true) {
                Toast.makeText(context, "Keranjang masih kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Handle checkout process
            Toast.makeText(context, "Berhasil checkout!", Toast.LENGTH_SHORT).show()
            viewModel.clearCart()
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            rvCart.visibility = View.GONE
            tvEmptyCart.visibility = View.VISIBLE
            btnCheckout.isEnabled = false
        } else {
            rvCart.visibility = View.VISIBLE
            tvEmptyCart.visibility = View.GONE
            btnCheckout.isEnabled = true
        }
    }

    private fun updateTotalPrice() {
        val total = viewModel.getTotalPrice()
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(total)
        tvTotalPrice.text = formattedPrice
    }
}