package com.example.myapplicationjetpackcompose.model

data class ht_thongtinhdoanhnghiep (

    val ten_dv: String? = "",
    val ten_viettat: String? = "",
    val slogan: String? = "",
    val hinhanh_logo: String? = "",

    )

data class response_ht_thongtinhdoanhnghiep (

    val data : ht_thongtinhdoanhnghiep

): response_status ()
