package com.example.teste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productIdTextView.text = product.id.toString()
        holder.productNameTextView.text = product.name
        holder.productCategoryTextView.text = product.category
        holder.productPriceTextView.text = "R$ ${product.price}"    // Exibe o preço do produto, formatado como R$


        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productIdTextView: TextView = view.findViewById(R.id.tvProductId)
        val productNameTextView: TextView = view.findViewById(R.id.tvProductName)
        val productCategoryTextView: TextView = view.findViewById(R.id.tvProductCategory)
        val productPriceTextView: TextView = view.findViewById(R.id.tvProductPrice)  // Referência para o preço
    }
}
