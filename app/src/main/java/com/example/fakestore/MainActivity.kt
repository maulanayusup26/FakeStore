package com.example.fakestore

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.Adapter.CategoryAdapter
import com.example.fakestore.Adapter.ProductAdapter
import com.example.fakestore.LogIn.data.model.HomeViewModel
import com.example.fakestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        // Init adapters
        productAdapter = ProductAdapter(listOf(), {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        },this)

        categoryAdapter = CategoryAdapter(emptyList()) {
            viewModel.filterByCategory(it)
        }

        // Set RecyclerViews
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = productAdapter

        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoryAdapter

        // Observe ViewModel
        viewModel.products.observe(this) {
            productAdapter.updateData(it)
        }

        viewModel.categories.observe(this) {
            categoryAdapter.updateData(it)
        }

        viewModel.loading.observe(this) {
            // Loading
        }

        // Load data
        viewModel.loadCategories()
        viewModel.loadProducts()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
                true
            }
            R.id.action_profile -> {
                ProfileBottomSheet().show(supportFragmentManager, "ProfileBottomSheet")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}