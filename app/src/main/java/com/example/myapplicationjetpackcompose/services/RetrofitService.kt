package com.example.myapplicationjetpackcompose.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(url_api.httpAdress)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            //.client()
            .build()




    }

    val IRetrofitService by lazy {

        retrofit.create(com.example.myapplicationjetpackcompose.services.IRetrofitService::class.java)
    }
}