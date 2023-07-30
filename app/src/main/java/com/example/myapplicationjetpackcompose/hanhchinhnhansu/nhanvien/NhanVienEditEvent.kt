package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

sealed class NhanVienEditEvent {

    object LoadData : NhanVienEditEvent()

    object SaveData : NhanVienEditEvent()


    object UploadImageNhanvienDaidien : NhanVienEditEvent()

}
