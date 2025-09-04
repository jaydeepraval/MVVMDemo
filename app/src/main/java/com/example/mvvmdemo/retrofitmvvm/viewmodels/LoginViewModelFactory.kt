package com.example.mvvmdemo.retrofitmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.retrofitmvvm.repository.LoginRepository
import com.mvvm.jetpacklogindemo.models.LoginRequest

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}