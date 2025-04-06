package com.example.fakestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fakestore.LogIn.data.model.CartEntity
import com.example.fakestore.LogIn.data.model.CartViewModel
import com.example.fakestore.LogIn.data.model.Product
import com.example.fakestore.LogIn.data.repository.CartViewModelFactory
import com.example.fakestore.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: CartViewModel

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = CartViewModelFactory(application)
        viewModel = ViewModelProvider(this,factory)[CartViewModel::class.java]

        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        if (product != null) {
            binding.tvTitle.text = product.title
            binding.tvPrice.text = "$${product.price}"
            binding.tvDescription.text = product.description
            Glide.with(this)
                .load(product.image)
                .into(binding.ivProductImage)

            binding.btnAddToCart.setOnClickListener {
                val cartItem = CartEntity(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    image = product.image,
                    quantity = 1
                )
                viewModel.insertItem(cartItem)
                Toast.makeText(this, "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Produk tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
