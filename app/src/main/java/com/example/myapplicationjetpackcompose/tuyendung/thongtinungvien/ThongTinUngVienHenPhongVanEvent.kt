package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent

sealed class ThongTinUngVienHenPhongVanEvent {

    class EmailChanged(val emailAddress: String): ThongTinUngVienHenPhongVanEvent()

}