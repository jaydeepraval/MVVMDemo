package com.example.mvvmdemo.retrofitmvvm.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.retrofitmvvm.repository.LoginRepository
import com.example.mvvmdemo.retrofitmvvm.repository.LoginState
import com.mvvm.jetpacklogindemo.models.LoginModel
import com.mvvm.jetpacklogindemo.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val respository: LoginRepository
) : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun performLogin() {
        val u = username.get()?.trim() ?: ""
        val p = password.get()?.trim() ?: ""
        Log.e("Clicked: ","$u $p")
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            if (u.isNotEmpty() && p.isNotEmpty()) {
                    val request = LoginRequest(u, p)
                    val result = respository.login(request)
                    if (result.isSuccess) {
                        _loginState.value = LoginState.Success(result.getOrThrow())
                        Log.e("Jaydeep", result.getOrThrow().toString())
                    } else {
                        _loginState.value =
                            LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                        Log.e("Jaydeep", result.exceptionOrNull()?.printStackTrace().toString())
                    }
            } else {
                // Handle empty fields
                Log.e("Jaydeep", "Empty fields")
                _loginState.value =
                    LoginState.Error("Empty fields")
            }
        }
    }
}