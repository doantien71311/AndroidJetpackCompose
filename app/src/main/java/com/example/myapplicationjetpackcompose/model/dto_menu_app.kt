package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class dto_menu_app (

    val ma_chucnang: String? = null,
    var ten_chucnang: String? = null,
    var url_hinhanh: String? = null,
    var icon_img: String? = null,
    var menu_app_chitiet: List<dto_menu_app_chitiet>? = mutableListOf()
)

@Serializable
data class dto_menu_app_chitiet (

    var ma_chucnang: String? = null,
    var ten_chucnang: String? = null,
    var url_hinhanh: String? = null,
    var ma_chucnang_cha: String? = null,
    var icon_img: String? = null,

    )
@Serializable
data class  response_dto_menu_app (
    var data: List<dto_menu_app>
): response_status()



