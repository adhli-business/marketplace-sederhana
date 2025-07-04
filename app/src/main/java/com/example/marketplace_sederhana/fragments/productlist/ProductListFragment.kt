package com.example.marketplace_sederhana.fragments.productlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.marketplace_sederhana.MainActivity
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.fragments.cart.CartFragment
import com.example.marketplace_sederhana.fragments.login.LoginFragment
import com.example.marketplace_sederhana.fragments.productdetail.ProductDetailFragment
import com.example.marketplace_sederhana.fragments.cart.CartViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ProductListFragment : Fragment() {
    private lateinit var viewModel: ProductListViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var fabCart: ExtendedFloatingActionButton
    private lateinit var tvCartBadge: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set toolbar title
        (activity as AppCompatActivity).supportActionBar?.title = "Daftar Produk"

        setupViews(view)
        setupViewModels()
        setupRecyclerView()
        observeCart()
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_products)
        fabCart = view.findViewById(R.id.fab_cart)
        tvCartBadge = view.findViewById(R.id.tv_cart_badge)

        fabCart.setOnClickListener {
            (activity as MainActivity).loadFragment(CartFragment())
        }
    }

    private fun setupViewModels() {
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter { product ->
            val fragment = ProductDetailFragment.newInstance(product)
            (activity as MainActivity).loadFragment(fragment)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel.getProducts().observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
        }
    }

    private fun observeCart() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            val totalItems = items.sumOf { it.quantity }
            updateCartBadge(totalItems)

            // Update FAB text with total items
            if (totalItems > 0) {
                fabCart.text = "Keranjang ($totalItems)"
            } else {
                fabCart.text = "Keranjang"
            }
        }
    }

    private fun updateCartBadge(count: Int) {
        if (count > 0) {
            tvCartBadge.apply {
                isVisible = true
                text = if (count > 99) "99+" else count.toString()
            }
        } else {
            tvCartBadge.isVisible = false
        }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_product_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        (activity as MainActivity).loadFragment(CartFragment())
                        true
                    }
                    R.id.action_logout -> {
                        showLogoutConfirmation()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                // Clear cart when logging out
                cartViewModel.clearCart()
                // Navigate to login screen
                (activity as MainActivity).loadFragment(LoginFragment())
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    fun showAddToCartFeedback(productName: String) {
        Snackbar.make(
            requireView(),
            "$productName ditambahkan ke keranjang",
            Snackbar.LENGTH_SHORT
        ).setAnchorView(fabCart).show()
    }
}