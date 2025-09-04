package com.mvvm.jetpacklogindemo.models

data class Auth(
    val aud: String,
    val auth_time: Int,
    val email: String,
    val email_verified: Boolean,
    val event_id: String,
    val exp: Int,
    val iat: Int,
    val isOperationUser: Boolean,
    val iss: String,
    val name: String,
    val phone_number: String,
    val phone_number_verified: Boolean,
    val role: String,
    val sub: String,
    val token_use: String
)