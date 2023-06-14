package com.example.myapplicationjetpackcompose.mainmenu

sealed class MainMenuDestination (val route: String)
{
    object  MAINMENU: MainMenuDestination("MAINMENU")
    object  DANHMUC_NhanVien: MainMenuDestination("DANHMUC_NhanVien")
    object  Home: MainMenuDestination("home")
    object  NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet: MainMenuDestination("NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet")

    object  NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet: MainMenuDestination("NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet")

}