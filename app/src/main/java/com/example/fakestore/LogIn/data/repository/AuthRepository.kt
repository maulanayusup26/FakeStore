package com.example.fakestore.LogIn.data.repository

import com.example.fakestore.LogIn.data.model.LoginRequest
import com.example.fakestore.LogIn.data.model.LoginResponse
import com.example.fakestore.LogIn.data.network.RetrofitInstance
import retrofit2.Call

class AuthRepository {
    fun loginUser(request: LoginRequest): Call<LoginResponse> {
        return RetrofitInstance.api.login(request)
    }
}
