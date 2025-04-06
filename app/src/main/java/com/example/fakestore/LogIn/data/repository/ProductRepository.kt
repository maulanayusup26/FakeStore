package com.example.fakestore.LogIn.data.repository

import com.example.fakestore.LogIn.data.model.Product
import com.example.fakestore.LogIn.data.network.ApiClient

class ProductRepository {
    private val api = ApiClient.apiService

    suspend fun getProducts(): List<Product> = api.getAllProducts()
    suspend fun getCategories(): List<String> = api.getAllCategories()
    suspend fun getProductsByCategory(category: String): List<Product> = api.getProductsByCategory(category)
}