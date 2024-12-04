package com.example.teste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductActivity : ComponentActivity() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var confirmButton: Button

    private val productList = listOf(
        Product(1, "Dermaplaning", "Estetica", 60.0),
        Product(2, "Extensão de Cilios", "Estetica", 30.0),
        Product(3, "Limpeza de Pele", "Estetica", 50.0),
        Product(4, "Microagulhamento", "Estetica", 80.0),
        Product(5, "Micropigmentação Labial", "Estetica", 100.0)
    )

    private var selectedProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productRecyclerView = findViewById(R.id.recycler_view)
        confirmButton = findViewById(R.id.confirm_schedule_button)

        productAdapter = ProductAdapter(productList) { product ->
            selectedProduct = product

            val selectedProductTextView = findViewById<TextView>(R.id.selected_product_name)
            selectedProductTextView.text = "Produto Selecionado: ${product.name}"
        }

        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = productAdapter

        confirmButton.setOnClickListener {
            selectedProduct?.let {

                val intent = Intent(this, ScheduleActivity::class.java)
                intent.putExtra("product_name", it.name)
                intent.putExtra("product_id", it.id)
                intent.putExtra("product_price", it.price)
                intent.putExtra("product_category", it.category)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Selecione um produto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
