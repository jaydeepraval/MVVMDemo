package com.mvvm.jetpacklogindemo.models

data class LoginModel(
    val accessToken: String,
    val idToken: String,
    val refreshToken: String
)