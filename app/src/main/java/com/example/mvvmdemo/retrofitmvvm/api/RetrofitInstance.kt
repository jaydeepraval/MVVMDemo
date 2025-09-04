package com.example.mvvmdemo.retrofitmvvm.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASEURL = "https://api.joulepoint.com"

    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: ApiInterface by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.joulepoint.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }

    /* private const val BASEURL = "https://api.joulepoint.com"

     private fun getInstance(): Retrofit {
         return Retrofit.Builder().baseUrl(BASEURL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }

     val apiInterface: ApiInterface = getInstance().create(ApiInterface::class.java)*/

}