package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class ht_thongtinhdoanhnghiep (

    val ten_dv: String? = "",
    val ten_viettat: String? = "",
    val slogan: String? = "",
    val hinhanh_logo: String? = "",

    )

@Serializable
data class response_ht_thongtinhdoanhnghiep (

    val data : ht_thongtinhdoanhnghiep = ht_thongtinhdoanhnghiep()

): response_status ()
