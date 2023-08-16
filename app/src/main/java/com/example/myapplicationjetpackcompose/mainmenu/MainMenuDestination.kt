package com.example.myapplicationjetpackcompose.mainmenu

import com.example.myapplicationjetpackcompose.EnumArgument




sealed class MainMenuDestination (val route: String)
{
    //region Khảo sát
    object  NHAPLIEU_KhaoSat_TuyenDung: MainMenuDestination("NHAPLIEU_KhaoSat_TuyenDung")
    object  NHAPLIEU_KhaoSat: MainMenuDestination("NHAPLIEU_KhaoSat")


    //region Khảo sát



    object  MAINMENU: MainMenuDestination("MAINMENU")

    //region Hành chính nhân sự

    object  DANHMUC_ChucVu: MainMenuDestination("DANHMUC_ChucVu")

    object  DANHMUC_NhanVien: MainMenuDestination("DANHMUC_NhanVien")
    object  DANHMUC_NhanVien_Edit: MainMenuDestination(
        EnumMainMenu.DANHMUC_NhanVien_Edit
            .plus( "?")
            .plus("${EnumArgument.keyvalue}={${EnumArgument.keyvalue}}")
            .plus( "${EnumArgument.tungay}={${EnumArgument.tungay}}")
            .plus( "${EnumArgument.denngay}={${EnumArgument.denngay}}")
    ) {
        fun passParamater(
            keyvalue: String = "",
            tungay: String = "",
            denngay: String = ""
        ): String {

          return EnumMainMenu.DANHMUC_NhanVien_Edit
                .plus("?")
                .plus(EnumArgument.keyvalue + "=$keyvalue")
                .plus(EnumArgument.tungay + "=$tungay")
                .plus(EnumArgument.denngay + "=$denngay")
        }
    }

    //endregion Hành chình nhân sự

    object  Home: MainMenuDestination("home")
    object  NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet: MainMenuDestination("NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet")

    object  NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet: MainMenuDestination(EnumMainMenu.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet) {
        fun passParamater(
            keyvalue: String = "",
            tungay: String = "",
            denngay: String = ""
        ): String {

            return EnumMainMenu.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet
                .plus("?")
                .plus(EnumArgument.keyvalue + "=$keyvalue")
                .plus(EnumArgument.tungay + "=$tungay")
                .plus(EnumArgument.denngay + "=$denngay")
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