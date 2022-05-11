package com.example.searchrecipeapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.searchrecipeapp.data.repositories.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val loginRepository : LoginRepository = LoginRepository(application)
    val userLiveData = loginRepository.userLiveData

    fun register(email : String, password: String){
        viewModelScope.launch {
            loginRepository.register(email, password)
        }
    }

    fun login(email : String, password: String){
        viewModelScope.launch {
            loginRepository.login(email, password)
        }
    }
}