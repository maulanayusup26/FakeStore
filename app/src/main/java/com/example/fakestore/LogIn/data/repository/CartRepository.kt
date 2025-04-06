package com.example.fakestore.LogIn.data.repository

import com.example.fakestore.LogIn.data.DataBase.AppDatabase
import com.example.fakestore.LogIn.data.model.CartEntity

class CartRepository(private val db: AppDatabase) {

    fun getCartItems() = db.cartDao().getAll()

    suspend fun insertItem(item: CartEntity) = db.cartDao().insert(item)

    suspend fun deleteItem(id: Int) = db.cartDao().delete(id)

    suspend fun updateItem(item: CartEntity) = db.cartDao().update(item)

    suspend fun clearCart() = db.cartDao().clearCart()
}