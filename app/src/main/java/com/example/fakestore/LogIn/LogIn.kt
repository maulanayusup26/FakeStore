package com.example.fakestore.LogIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fakestore.LogIn.data.model.CartViewModel
import com.example.fakestore.MainActivity
import com.example.fakestore.R
import com.example.fakestore.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(username, password)
        }

        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { token ->
                // Simpan token
                getSharedPreferences("user", Context.MODE_PRIVATE).edit()
                    .putString("token", token)
                    .apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            result.onFailure { e ->
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}