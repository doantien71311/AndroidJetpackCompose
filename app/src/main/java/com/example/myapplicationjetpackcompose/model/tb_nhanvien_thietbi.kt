package com.example.myapplicationjetpackcompose.model

import com.example.myapplicationjetpackcompose.services.UUIDSerializer
import com.google.type.DateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class tb_nhanvien_thietbi (

    @Serializable(with = UUIDSerializer::class)
    var id: UUID = UUID.randomUUID(),

    var ma_nv: String?  = null,
    var ma_nsd: String?  = null,
    var token_pcm: String?  = null,
    var installation_id: String?  = null,
    var nentang: String?  = null,
    var ghichu: String?  = null,
    var khongsudung: Int = 0,
//    var ngaytao: LocalDateTime? = null,
//    var nguoitao: String?  = null,
//    var ngaysua: LocalDateTime? = null,
//    var nguoisua: String?  = null,
//    var ngayxoa: LocalDateTime? = null,
//    var nguoixoa: String?  = null,
    var daxoa: Int = 0,
    var phienban: String?  = null,
    )

@Serializable
data class  response_tb_nhanvien_thietbi (
    var data: List<tb_nhanvien_thietbi>
): response_status()