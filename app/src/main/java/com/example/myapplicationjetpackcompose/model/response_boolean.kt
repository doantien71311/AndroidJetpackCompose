package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class response_boolean (
    var data: Boolean? = false
): response_status()