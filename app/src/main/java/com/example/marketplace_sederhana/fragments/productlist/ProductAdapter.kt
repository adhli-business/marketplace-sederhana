package com.example.marketplace_sederhana.fragments.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplace_sederhana.R
import com.example.marketplace_sederhana.models.Product
import java.text.NumberFormat
import java.util.*

class ProductAdapter(
    private val onItemClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        private val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        private val btnDetail: Button = itemView.findViewById(R.id.btn_detail)

        fun bind(product: Product) {
            tvName.text = product.name
            tvPrice.text = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                .format(product.price)

            // Load product image
            if (product.imageUrl != null) {
                Glide.with(ivProduct.context)
                    .load(product.imageUrl)
                    .placeholder(R.drawable.ic_cart_white)
                    .into(ivProduct)
            }

            btnDetail.setOnClickListener { onItemClick(product) }
            itemView.setOnClickListener { onItemClick(product) }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}