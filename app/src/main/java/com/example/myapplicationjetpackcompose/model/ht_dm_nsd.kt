package com.example.myapplicationjetpackcompose.model

data class ht_dm_nsd (

    var ma_nsd: String? = "",
    var matkhau: String? = ""
)

data class  response_ht_dm_nsd (
    var data: List<ht_dm_nsd>
): response_status()
