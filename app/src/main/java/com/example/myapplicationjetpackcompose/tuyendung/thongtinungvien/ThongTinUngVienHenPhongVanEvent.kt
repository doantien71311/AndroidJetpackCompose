package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

sealed class ThongTinUngVienHenPhongVanEvent {

    class EmailChanged(val emailAddress: String): ThongTinUngVienHenPhongVanEvent()
    class LinkPhongVanOnlineChanged(val link_phongvan_online: String): ThongTinUngVienHenPhongVanEvent()
    class DiaDiemHenPhongVanChanged(val diadiem_henphongvan: String): ThongTinUngVienHenPhongVanEvent()

    class IsPhongVanOnLineChanged(val is_phongvan_online: Boolean): ThongTinUngVienHenPhongVanEvent()

    class NgayHenPhongVanOnLineChanged(val year: Int, val month: Int, val day: Int): ThongTinUngVienHenPhongVanEvent()
    class ThoiGianHenPhongVanOnLineChanged(val hour: Int, val minute: Int): ThongTinUngVienHenPhongVanEvent()

    class IsNhacnhoChanged(val is_nhacnho: Boolean): ThongTinUngVienHenPhongVanEvent()

    class SophutNhacNhoOnLineChanged(val sophut_nhacnho: Int?): ThongTinUngVienHenPhongVanEvent()

}