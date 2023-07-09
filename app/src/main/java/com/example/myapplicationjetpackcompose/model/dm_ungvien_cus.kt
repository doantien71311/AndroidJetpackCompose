package com.example.myapplicationjetpackcompose.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplicationjetpackcompose.services.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class dm_ungvien_cus (

 @Serializable(with = UUIDSerializer::class)
 var id: UUID = UUID.randomUUID(),
 var ma_uv: String?  = null,
 var ten_uv: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngaysinh: LocalDateTime? = null,
 var gioitinh: String?  = null,
 var so_cmnd: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngaycap_cmnd: LocalDateTime? = null,
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
 var vitri_ungtuyen: String?  = null,
 var nganhnghe_lamviec: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_dangky: LocalDateTime? = null,
 var ma_nv_tuyendung: String?  = null,
 var duyet_dondangky: Int? = null,
 var ma_chinhanh_duyet: String?  = null,
 var ma_nv_tuyendung_duyet: String?  = null,
 var nguoiduyet: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngayduyet: LocalDateTime? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngaytao: LocalDateTime? = null,
 var nguoitao: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngaysua: LocalDateTime? = null,
 var nguoisua: String?  = null,
 var daxoa: Int = 0,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngayxoa: LocalDateTime? = null,
 var nguoixoa: String?  = null,
 var is_nopphieuphongvan: Boolean? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_nopphieuphongvan: LocalDateTime? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_henphongvan: LocalDateTime? = null,
 var diadiem_henphongvan: String?  = null,
 var duyet_henphongvan: Int? = null,
 var phongvan_mucthuphidenghi: String?  = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var phongvan_ngaynhanviec: LocalDateTime? = null,
 var phongvan_danhgiachung: String?  = null,
 var phongvan_thunhap_denghi: Double? = null,
 var phongvan_nguoiphongvan: String?  = null,
 var phongvan_lanphongvan: Int? = null,
 var is_phongvan_ketqua: Int? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_phongvan: LocalDateTime? = null,
 var is_nopphieuphongvan_daky: Boolean? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_nopphieuphongvan_daky: LocalDateTime? = null,
 var hinhanh_cmnd_mattruoc: String?  = null,
 var hinhanh_cmnd_matsau: String?  = null,
 var is_kichhoat: Boolean? = null,
 @Serializable(with = LocalDateTimeIso8601Serializer::class)
 var ngay_kichhoat: LocalDateTime? = null,
 var tinhtrang: Int? = null,
 var is_phongvan_online: Boolean? = null,
 var link_phongvan_online: String?  = null,
 var is_offline: String?  = null,
 var is_thamgia_hoithao: String?  = null,
 var is_banhang: String?  = null,
 var is_nhaptay: String?  = null,
 var is_dangky: Int? = null,
 var is_capcode: Int? = null,
 var ten_nv_tuyendung: String?  = null,
 var ma_chinhanh_tuyendung: String?  = null,
 var ten_chinhanh_tuyendung: String?  = null,
 var ma_chucvu_tuyendung: String?  = null,
 var ten_chucnang_tuyendung: String?  = null,
 var ma_nv: String?  = null,
 var ten_nv: String?  = null,
 var ma_chinhanh: String?  = null,
 var ten_chinhanh: String?  = null,
 var ma_chucvu: String?  = null,
 var ten_chucvu: String?  = null,
 var ma_nsd: String?  = null,
 var ten_nsd: String?  = null,
 var matkhau: String?  = null,
 var kichhoat_user: Int? = null,

 var is_nhacnho:  String? = null,
 var sophut_nhacnho:  Int? = null,

    //Tiến thêm vào để tạo hiệu ứng
 //Tiến thêm vào để không serialization khi pass data api
  @kotlinx.serialization.Transient
   var isAnimatedVisibility: MutableState<Boolean> = mutableStateOf(true),


    //Tiến thêm vào để kiểm tra lỗi email
    //Tiến thêm vào để không serialization khi pass data api
    @kotlinx.serialization.Transient
    var is_email_error: Boolean = true,

   //Tiến thêm vào để không serialization khi pass data api
   @kotlinx.serialization.Transient
    var message_email_error: String = "",

   //Tiến thêm vào để không serialization khi pass data api
   @kotlinx.serialization.Transient
   var is_error_link_phongvan_online: Boolean = false,

  //Tiến thêm vào để không serialization khi pass data api
  @kotlinx.serialization.Transient
  var message_error_link_phongvan_online: String? = "",


   //Tiến thêm vào để không serialization khi pass data api
   @kotlinx.serialization.Transient
   var message_error_diadiem_henphongvan: String? = "",

)

@Serializable
data class response_dm_ungvien_cus (

    val data : List<dm_ungvien_cus> = mutableListOf()

): response_status ()
