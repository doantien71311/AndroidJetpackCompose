package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

sealed  class KichHoatThanhVienEvent {

    class EmailChanged(val emailAddress: String): KichHoatThanhVienEvent()


}