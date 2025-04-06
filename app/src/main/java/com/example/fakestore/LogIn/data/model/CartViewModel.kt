package com.example.fakestore.LogIn.data.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.LogIn.data.DataBase.AppDatabase
import com.example.fakestore.LogIn.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao = AppDatabase.getDatabase(application).cartDao()
    private val repository = CartRepository(AppDatabase.getDatabase(application))

    fun insertItem(cart: CartEntity) {
        viewModelScope.launch {
            repository.insertItem(cart)
        }
    }

    fun getCartItems() = repository.getCartItems()

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repository.deleteItem(id)
        }
    }

    fun updateItem(item: CartEntity) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

}