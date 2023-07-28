package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienHenPhongVanEvent

sealed class NhanVienEvent {

    object LoadData : NhanVienEvent()

    object SaveData : NhanVienEvent()

}
