package com.example.fakestore.LogIn.data.network

import com.example.fakestore.LogIn.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @GET("products/categories")
    suspend fun getAllCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<Product>
}
