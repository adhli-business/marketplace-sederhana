package com.example.marketplace_sederhana.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.models.CartItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class CartAdapter(
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onItemRemoved: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        private val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        private val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        private val btnDecrease: ImageButton = itemView.findViewById(R.id.btn_decrease)
        private val btnIncrease: ImageButton = itemView.findViewById(R.id.btn_increase)
        private val btnRemove: ImageButton = itemView.findViewById(R.id.btn_remove)

        fun bind(item: CartItem) {
            val product = item.product
            tvProductName.text = product.name
            tvProductPrice.text = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                .format(product.price)
            tvQuantity.text = item.quantity.toString()

            // Handle image loading
            when {
                product.imageResId != null -> {
                    ivProduct.setImageResource(product.imageResId)
                }
                product.imageUrl != null -> {
                    Glide.with(ivProduct)
                        .load(product.imageUrl)
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .into(ivProduct)
                }
                else -> {
                    ivProduct.setImageResource(android.R.drawable.ic_menu_gallery)
                }
            }

            btnDecrease.setOnClickListener {
                if (item.quantity > 1) {
                    onQuantityChanged(item, item.quantity - 1)
                }
            }

            btnIncrease.setOnClickListener {
                onQuantityChanged(item, item.quantity + 1)
            }

            btnRemove.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}
