package com.example.fakestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.Adapter.CartAdapter
import com.example.fakestore.LogIn.data.model.CartEntity
import com.example.fakestore.LogIn.data.model.CartViewModel
import com.example.fakestore.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        adapter = CartAdapter(
            mutableListOf(),
            onDelete = { item -> viewModel.deleteItem(item.id) },
            onUpdate = { item -> viewModel.updateItem(item) }
        )


        binding.rvCart.layoutManager = LinearLayoutManager(this)
        binding.rvCart.adapter = adapter

        viewModel.getCartItems().observe(this) { cartItems ->
            adapter.updateData(cartItems)
            updateTotal(cartItems)
        }

        binding.btnCheckout.setOnClickListener {
            viewModel.getCartItems().observe(this) { cartList ->
                if (cartList.isNullOrEmpty()) {
                    Toast.makeText(this, "Keranjang masih kosong!", Toast.LENGTH_SHORT).show()
                } else {
                    // Lanjutkan proses checkout
                    Toast.makeText(this, "Berhasil Checkout", Toast.LENGTH_SHORT).show()
                    // clear cart
                    cartList.forEach {
                        viewModel.deleteItem(it.id)
                    }
                }
            }
        }
    }

    private fun updateTotal(cartItems: List<CartEntity>) {
        val total = cartItems.sumOf { it.price * it.quantity }
        binding.tvTotal.text = "Total: $${"%.2f".format(total)}"
    }
}