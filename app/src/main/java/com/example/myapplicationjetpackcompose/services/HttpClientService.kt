package com.example.myapplicationjetpackcompose.services

import com.example.myapplicationjetpackcompose.url_api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object HttpClientService {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(url_api.httpAdress)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    }

    val IHttpClientService by lazy {

        retrofit.create(com.example.myapplicationjetpackcompose.services.IHttpClientService::class.java)
    }
}