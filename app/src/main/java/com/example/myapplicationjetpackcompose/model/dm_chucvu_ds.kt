package com.example.myapplicationjetpackcompose.model

import com.example.myapplicationjetpackcompose.services.UUIDSerializer
import com.google.type.DateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class dm_chucvu_ds (

    @Serializable(with = UUIDSerializer::class)
    var id: UUID = UUID.randomUUID(),

    var ma_chucvu: String?  = null,
    var ma_chucvu_old: String?  = null,
    var ma_chucvu_cha: String?  = null,
    var ma_chucvu_daily: String?  = null,
    var ten_chucvu: String?  = null,
    var ten_viettat: String?  = null,
    var dotuoi_min: Int? = null,
    var mota_gioithieu: String?  = null,
    var is_sodotochuc: Boolean? = null,
    var stt: Int? = null,
    var ghichu: String?  = null,
    var khongsudung: Int = 0,
    var url_hinhanh_app: String? = null,
//    var ngaytao: DateTime? = null,
//    var nguoitao: String?  = null,
//    var ngaysua: DateTime? = null,
//    var nguoisua: String?  = null,
//    var ngayxoa: DateTime? = null,
//    var nguoixoa: String?  = null,
//    var daxoa: Int = 0,
//    var phienban: String?  = null,
    var is_dangky_tuyendung: Boolean? = null,
    )
@Serializable
data class response_dm_chucvu_ds (

    val data : List<dm_chucvu_ds> = mutableListOf()

): response_status ()