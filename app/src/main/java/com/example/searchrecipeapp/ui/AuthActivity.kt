package com.example.searchrecipeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.searchrecipeapp.MainActivity
import com.example.searchrecipeapp.R
import com.example.searchrecipeapp.databinding.ActivityAuthBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.userLiveData.observe(this, object : Observer<FirebaseUser>{
            override fun onChanged(firebaseUser: FirebaseUser?) {
                if(firebaseUser != null){
                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }

        })
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if(setUpRegistrationButtonValidation()){
                viewModel.register(email, password)
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if(setUpRegistrationButtonValidation()){
                viewModel.login(email, password)
            }
        }
    }
    private fun setUpRegistrationButtonValidation(): Boolean {
        binding.apply {
            if (emailEditText.text.isNullOrBlank() &&
                passwordEditText.text.isNullOrBlank()
            ) {
                Snackbar.make(binding.registerButton, "Неправильно введены данные!", Snackbar.LENGTH_LONG)
                    .setAction("OK") {
                    }.show()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()) {
                Snackbar.make(binding.registerButton, "Неверный email!", Snackbar.LENGTH_LONG)
                    .setAction("OK") {
                    }.show()
                return false
            } else if (passwordEditText.text!!.length < 4 || passwordEditText.text!!.length > 30) {
                Snackbar.make(
                    binding.registerButton,
                    "Пароль должен быть длиной от 4 до 30 символов!",
                    Snackbar.LENGTH_LONG
                ).setAction("OK") {
                }.show()
                return false
            }
            return true
        }
    }
}