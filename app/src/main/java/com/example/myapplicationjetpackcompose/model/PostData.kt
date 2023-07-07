package com.example.myapplicationjetpackcompose.model

import com.example.myapplicationjetpackcompose.services.url_api
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostData (
    //@SerialName("Username")
    val Username: String = url_api.username,
    //@SerialName("Password")
    val Password: String = url_api.password
)
//{
//    init {
//        serializer()
//    }
//}