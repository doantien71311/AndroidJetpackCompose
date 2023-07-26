package com.example.myapplicationjetpackcompose.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplicationjetpackcompose.services.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class dm_nhanvien_cus (

 @Serializable(with = UUIDSerializer::class)
 var id: UUID = UUID.randomUUID(),
 var ma_nv: String?  = null,
 var ten_nv: String?  = null,


)

@Serializable
data class response_dm_nhanvien_cus (

    val data : List<dm_nhanvien_cus> = mutableListOf()

): response_status ()
