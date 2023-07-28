package com.example.myapplicationjetpackcompose.mainmenu

import com.example.myapplicationjetpackcompose.EnumArgument




sealed class MainMenuDestination (val route: String)
{
    object  MAINMENU: MainMenuDestination("MAINMENU")
    object  DANHMUC_NhanVien: MainMenuDestination("DANHMUC_NhanVien")
    object  Home: MainMenuDestination("home")
    object  NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet: MainMenuDestination("NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet")

    object  NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet: MainMenuDestination("NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet")
    {
        fun passParamater(keyvalue: String = "", tungay: String = "", denngay: String = ""): String {

            return EnumMainMenu.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet
                .plus("?")
                .plus(EnumArgument.keyvalue + "=$keyvalue")
                .plus( EnumArgument.tungay + "=$tungay")
                .plus( EnumArgument.denngay + "=$denngay")
        }
    }

    object  NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet: MainMenuDestination (

//        EnumMainMenu.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet + "?"
//                + "${EnumArgument.keyvalue}={${EnumArgument.keyvalue}}"
//                + "${EnumArgument.tungay}={${EnumArgument.tungay}}"
//                + "${EnumArgument.denngay}={${EnumArgument.denngay}}"

                EnumMainMenu.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet
                    .plus( "?")
                    .plus("${EnumArgument.keyvalue}={${EnumArgument.keyvalue}}")
                    .plus( "${EnumArgument.tungay}={${EnumArgument.tungay}}")
                    .plus( "${EnumArgument.denngay}={${EnumArgument.denngay}}")

    ) {
        fun passParamater(keyvalue: String = "", tungay: String = "", denngay: String = ""): String
        {

//            return EnumMainMenu.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet + "?" + EnumArgument.keyvalue + "=$keyvalue" + EnumArgument.tungay + "=$tungay" + EnumArgument.denngay + "=$denngay"

            return EnumMainMenu.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet
                .plus("?")
                .plus(EnumArgument.keyvalue + "=$keyvalue")
                .plus( EnumArgument.tungay + "=$tungay")
                .plus( EnumArgument.denngay + "=$denngay")

        }

    }






    object  LOGIN: MainMenuDestination("LOGIN")


    object  CAIDAT: MainMenuDestination("CAIDAT")

    object  CAIDAT_THONGBAO: MainMenuDestination("CAIDAT_THONGBAO")

}