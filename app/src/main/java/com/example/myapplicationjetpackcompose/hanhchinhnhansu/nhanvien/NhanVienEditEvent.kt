package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent
import java.io.File
import java.io.InputStream

sealed class NhanVienEditEvent {

    object LoadData : NhanVienEditEvent()

    object SaveData : NhanVienEditEvent()

    class UploadImageNhanvienDaidien (val file: File) : NhanVienEditEvent ()

}
