package com.example.fakestore.LogIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.LogIn.data.model.LoginRequest
import com.example.fakestore.LogIn.data.model.LoginResponse
import com.example.fakestore.LogIn.data.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        repository.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    _loginResult.postValue(Result.success(token ?: ""))
                } else {
                    _loginResult.postValue(Result.failure(Exception("Login gagal")))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginResult.postValue(Result.failure(Exception("Error: ${t.message}")))
            }
        })
    }
}
