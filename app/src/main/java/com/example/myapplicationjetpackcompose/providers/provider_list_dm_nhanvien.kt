package com.example.myapplicationjetpackcompose.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus

class provider_list_dm_nhanvien: PreviewParameterProvider<List<dm_nhanvien_cus>> {

    override val values = sequenceOf(

        listOf(

            dm_nhanvien_cus(
                ma_nv = "NV0001",
                ten_nv = "tên nhân viên 001",
                hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
                ma_chucvu = "AGG",
                ten_chucvu = "Chuyên viên tư vấn cấp cao lắm luôn đó",
                ma_chinhanh = "CN0001",
                ten_chinhanh = "Văn phòng 00001 khu vực đông nam bộ",
                dienthoai_lienhe = "0846356995",
                email = "buivantienthĩ@gmail.com",
                ngaysinh = LocalDateTimeGetNow(),
                ngayvaolam = LocalDateTimeGetNow(),
            ),
            dm_nhanvien_cus(
                ma_nv = "NV0002",
                ten_nv = "tên nhân viên 002 09877",
                hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
                ma_chucvu = "AG",
                ten_chucvu = "Chuyên viên tư vấn cấp cao lắm luôn đó",
                ma_chinhanh = "CN0001",
                ten_chinhanh = "Văn phòng 00001 khu vực đông nam bộ",
                dienthoai_lienhe = "0846356995",
                email = "buivantienthĩ@gmail.com",
                ngaysinh = LocalDateTimeGetNow(),
                ngayvaolam = LocalDateTimeGetNow(),
            ),

            dm_nhanvien_cus(
                ma_nv = "NV0003",
                ten_nv = "tên nhân viên 003",
                hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
                ma_chucvu = "AG",
                ten_chucvu = "Chuyên viên tư vấn cấp cao lắm luôn đó",
                ma_chinhanh = "CN0001",
                ten_chinhanh = "Văn phòng 00001 khu vực đông nam bộ",
                dienthoai_lienhe = "0846356995",
                email = "buivantienthĩ@gmail.com",
                ngaysinh = LocalDateTimeGetNow(),
                ngayvaolam = LocalDateTimeGetNow(),
            )

        )
    )
    override val count: Int = values.count()


}