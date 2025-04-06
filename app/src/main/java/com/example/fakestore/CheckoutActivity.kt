package com.example.fakestore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.Adapter.CheckoutAdapter
import com.example.fakestore.LogIn.data.model.CartViewModel
import com.example.fakestore.LogIn.data.repository.CartViewModelFactory
import com.example.fakestore.databinding.ActivityCheckoutBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckoutActivity : AppCompatActivity() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var adapter: CheckoutAdapter
    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(application)
        )[CartViewModel::class.java]



        adapter = CheckoutAdapter()
        binding.rvSummary.adapter = adapter
        binding.rvSummary.layoutManager = LinearLayoutManager(this)

        cartViewModel.getCartItems().observe(this) { cartItems ->
            adapter.setData(cartItems)

            val total = cartItems.sumOf { it.quantity * it.price.toInt() }
            binding.tvTotal.text = "Total: Rp $total"
        }

        binding.btnConfirm.setOnClickListener {
            Toast.makeText(this, "Pembayaran berhasil!", Toast.LENGTH_SHORT).show()
            // Kosongkan cart
            lifecycleScope.launch {
                cartViewModel.clearCart()
                delay(1000)

                val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}
