package com.example.myapplicationjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class dto_paramater(
    var ma_nv: String,
    var ma_nsd: String,
    var nguoitao: String,

) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "ma_nv" to ma_nv,
            "ma_nsd" to ma_nsd,
            "nguoitao" to nguoitao,
        )

}
}
