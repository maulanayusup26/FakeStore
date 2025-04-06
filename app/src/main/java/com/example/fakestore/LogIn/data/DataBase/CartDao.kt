package com.example.fakestore.LogIn.data.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fakestore.LogIn.data.model.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: CartEntity)

    @Query("SELECT * FROM cart")
    fun getAll(): LiveData<List<CartEntity>>

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun delete(id: Int)

    @Update
    suspend fun update(cart: CartEntity)

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}