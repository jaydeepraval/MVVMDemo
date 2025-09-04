package com.example.mvvmdemo.retrofitmvvm.repository

import com.mvvm.jetpacklogindemo.models.LoginModel

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val response: LoginModel) : LoginState()
    data class Error(val message: String) : LoginState()

    data class FIELDEMPTY(val message: String):LoginState()
}