package com.example.myapplicationjetpackcompose.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplicationjetpackcompose.services.UUIDSerializer
import com.example.myapplicationjetpackcompose.services.url_api
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class PostData (
    //@SerialName("Username")
    val Username: String? = url_api.username,
    //@SerialName("Password")
    val Password: String? = url_api.password,


    )
