package com.example.myapplicationjetpackcompose.model

data class dto_menu_app (

    var ma_chucnang: String? = "",
    var ten_chucnang: String? = "",
    var url_hinhanh: String? = "",
    var menu_app_chitiet: List<dto_menu_app_chitiet>
)

data class dto_menu_app_chitiet (

    var ma_chucnang: String? = "",
    var ten_chucnang: String? = "",
    var url_hinhanh: String? = "",

    )

data class  response_dto_menu_app (
    var data: List<dto_menu_app>
): response_status()



