package com.example.mvvmdemo.retrofitmvvm.api

import com.mvvm.jetpacklogindemo.models.ChargerModel
import com.mvvm.jetpacklogindemo.models.LoginModel
import com.mvvm.jetpacklogindemo.models.LoginRequest
import com.mvvm.jetpacklogindemo.models.ProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("/users/logIn")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginModel>

    @GET("/users/whoAmI")
    suspend fun profile(
        @Header("Authorization") token: String
    ): Response<ProfileModel>

    @GET("/users/charger?custom=true")
    suspend fun chargerLists(): Response<ChargerModel>
}