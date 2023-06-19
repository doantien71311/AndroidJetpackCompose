package com.example.myapplicationjetpackcompose.model

import com.google.type.DateTime
import java.util.UUID

data class tb_nhanvien_thietbi (

    var id: UUID = UUID.randomUUID(),
    var ma_nv: String?  = null,
    var ma_nsd: String?  = null,
    var token_pcm: String?  = null,
    var installation_id: String?  = null,
    var nentang: String?  = null,
    var ghichu: String?  = null,
    var khongsudung: Int = 0,
    var ngaytao: DateTime? = null,
    var nguoitao: String?  = null,
    var ngaysua: DateTime? = null,
    var nguoisua: String?  = null,
    var ngayxoa: DateTime? = null,
    var nguoixoa: String?  = null,
    var daxoa: Int = 0,
    var phienban: String?  = null,
    )

data class  response_tb_nhanvien_thietbi (
    var data: List<tb_nhanvien_thietbi>
): response_status()