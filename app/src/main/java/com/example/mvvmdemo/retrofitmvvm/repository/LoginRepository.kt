package com.example.mvvmdemo.retrofitmvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.retrofitmvvm.api.ApiInterface
import com.mvvm.jetpacklogindemo.models.LoginModel
import com.mvvm.jetpacklogindemo.models.LoginRequest

class LoginRepository(private val service: ApiInterface) {
   /* private val _loginResult = MutableLiveData<LoginModel>() // For UI states
    val loginResult: LiveData<LoginModel> get() = _loginResult*/

    suspend fun login(request: LoginRequest): Result<LoginModel> {
        return try {
            val response = service.login(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}