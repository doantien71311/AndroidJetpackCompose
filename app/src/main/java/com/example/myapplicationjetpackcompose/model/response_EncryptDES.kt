package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class response_EncryptDES (

    var data: String? = ""

): response_status()