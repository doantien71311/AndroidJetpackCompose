package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
open class response_data {

    val status: String? = ""
    val message: String? = ""
    val errorCode: String? = ""
    val data: String? = ""

}