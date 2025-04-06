package com.example.fakestore.LogIn.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.LogIn.data.repository.ProductRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repo = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> get() = _categories

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun loadProducts() {
        viewModelScope.launch {
            _loading.value = true
            _products.value = repo.getProducts()
            _loading.value = false
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repo.getCategories()
        }
    }

    fun filterByCategory(category: String) {
        viewModelScope.launch {
            _loading.value = true
            _products.value = repo.getProductsByCategory(category)
            _loading.value = false
        }
    }
}