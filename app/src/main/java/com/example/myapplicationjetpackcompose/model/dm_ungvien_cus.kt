package com.example.myapplicationjetpackcompose.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.google.type.DateTime
import java.util.UUID

data class dm_ungvien_cus (
    var id: UUID = UUID.randomUUID(),
    var ma_uv: String?  = null,
    var ten_uv: String?  = null,
    var ngaysinh: DateTime? = null,
    var gioitinh: String?  = null,
    var so_cmnd: String?  = null,
    var ngaycap_cmnd: DateTime? = null,
    var noicap_cmnd: String?  = null,
    var diachi_thuongtru: String?  = null,
    var noiohientai: String?  = null,
    var so_dienthoai: String?  = null,
    var email: String?  = null,
    var ma_sothue: String?  = null,
    var so_taikhoan_nganhang: String?  = null,
    var ten_nganhang: String?  = null,
    var chinhanh_nganhang: String?  = null,
    var trinhdo_hocvan: String?  = null,
    var trinhdo_hocvan_khac: String?  = null,
    var tinhtrang_honnhan: String?  = null,
    var tinhtrang_honnhan_socon: String?  = null,
    var nguoigioithieu_ten: String?  = null,
    var nguoigioithieu_chucvu: String?  = null,
    var nguoigioithieu_dienthoai: String?  = null,
    var hinhanh_canhan: String?  = null,
    var vitri_ungtuyen: String? = null,
    var nganhnghe_lamviec: String?  = null,
    var ngay_dangky: DateTime? = null,
    var ma_nv_tuyendung: String?  = null,
    var duyet_dondangky: Int? = null,
    var ma_chinhanh_duyet: String?  = null,
    var ma_nv_tuyendung_duyet: String?  = null,
    var nguoiduyet: String?  = null,
    var ngayduyet: DateTime? = null,
    var ngaytao: DateTime? = null,
    var nguoitao: String?  = null,
    var ngaysua: DateTime? = null,
    var nguoisua: String?  = null,
    var daxoa: Int = 0,
    var ngayxoa: DateTime? = null,
    var nguoixoa: String?  = null,
    var is_nopphieuphongvan: Boolean? = null,

    var ngay_nopphieuphongvan: DateTime? = null,
    var ngay_henphongvan: DateTime? = null,
    var diadiem_henphongvan: String?  = null,
    var duyet_henphongvan: Int? = null,

    var phongvan_mucthuphidenghi: String?  = null,
    var phongvan_ngaynhanviec: DateTime? = null,
    var phongvan_danhgiachung: String?  = null,
    var phongvan_thunhap_denghi: Double? = null,
    var phongvan_nguoiphongvan: String?  = null,
    var phongvan_lanphongvan: Int? = null,
    var is_phongvan_ketqua: Int? = null,
    var ngay_phongvan: DateTime? = null,
    var is_nopphieuphongvan_daky: Boolean? = null,
    var ngay_nopphieuphongvan_daky: DateTime? = null,
    var hinhanh_cmnd_mattruoc: String?  = null,
    var hinhanh_cmnd_matsau: String?  = null,
    var is_kichhoat: Boolean? = null,
    var ngay_kichhoat: DateTime? = null,
    var tinhtrang: Int? = null,

    var is_phongvan_online: Boolean? = null,
    var link_phongvan_online: String?  = null,



    var is_offline: String?  = null,
    var is_thamgia_hoithao: String?  = null,
    var is_banhang: String?  = null,
    var is_nhaptay: String?  = null,

    //
    var isAnimatedVisibility: MutableState<Boolean> = mutableStateOf(true),
)

data class response_dm_ungvien_cus (

    val data : dm_ungvien_cus = dm_ungvien_cus()

): response_status ()
