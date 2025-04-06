package com.example.fakestore.LogIn.data.network

import com.example.fakestore.LogIn.data.model.LoginRequest
import com.example.fakestore.LogIn.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
