package com.example.myapplicationjetpackcompose.mainmenu.homepage

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuEvent
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuItemsScreen
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainMenuTabsHomePageScreen(
    navController: NavController,
    context: Context,
    state: List<dto_menu_app>,
    onEvent: (MainMenuEvent) -> Unit
) {

    LazyColumn(
        //  modifier = Modifier.verticalScroll(rememberScrollState())
    )

    {

        itemsIndexed(
            items = state.toTypedArray()

        ) { index, item ->

            Text(
                text = item.ten_chucnang ?: "",
                //  modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            MainMenuTabsHomePageColumnSrceen(
                navController,
                context,
                item.menu_app_chitiet!!.toTypedArray()
            )

        }


    }

}

@Composable
fun MainMenuTabsHomePageColumnSrceen(
    navController: NavController,
    context : Context,
    para: Array<dto_menu_app_chitiet>,
) {

    LazyRow (
        // modifier = Modifier.horizontalScroll(rememberScrollState()),

    ) {
        itemsIndexed (
            items =  para
        )  { index, item ->

            MainMenuTabsHomePageItemSreen(navController,
                context,
                item,
            )

        }
    }

}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuTabsHomePageScreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        //region khởi taọ dữ menu test
        var list = mutableListOf<dto_menu_app>()


        //region khởi tạo menu tài liệu
        var lis_tailieu = mutableListOf<dto_menu_app_chitiet>()
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_BanHang", "Tài liệu bán hàng", "", )
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_TuyenDung", "Tài liệu tuyển dụng", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_DaoTao", "Tài liệu FWM", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_DuHoc", "Tài liệu du học", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_NhanTho", "Tài liệu FWM", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_PhiNhanTho", "Tài liệu đào tạo phi nhân thọ", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_PhiNhanTho", "Tài liệu đào tạo phi nhân thọ", "")
        )
        lis_tailieu.add(
            dto_menu_app_chitiet("DANHMUC_TaiLieu_UpLoad", "Tài liệu Upload", "")
        )
        list.add( dto_menu_app(ma_chucnang = "DANHMUC_Sub_TaiLieu", ten_chucnang = "Tài liệu", menu_app_chitiet = lis_tailieu ))

        //endregion khởi tạo menu tài liệu


        //region khởi tạo menu nhân sự
        var lis_nhansu = mutableListOf<dto_menu_app_chitiet>()
        lis_nhansu.add(
            dto_menu_app_chitiet("DANHMUC_ChucVu", "Chức vụ", "")
        )
        lis_nhansu.add(
            dto_menu_app_chitiet("DANHMUC_VanPhong", "Văn phòng", "")
        )
        lis_nhansu.add(
            dto_menu_app_chitiet("DANHMUC_NhanVien", "Nhân sự", "")
        )
        lis_nhansu.add(
            dto_menu_app_chitiet("DANHMUC_NhanVien_QuaTrinhLamViec", "Quá trỉnh làm việc", "")
        )
        list.add( dto_menu_app(ma_chucnang = "DANHMUC_Sub_NhanSu", ten_chucnang = "Hành chính nhân sự", menu_app_chitiet = lis_nhansu ))

        //endregion khởi tạo menu khảo sát


        //region khởi tạo menu khảo sát
//        var lis_khaosat = mutableListOf<dto_menu_app_chitiet>()
//        lis_khaosat.add(
//            dto_menu_app_chitiet("CN01-01", "Chức năng 01 - 01", "", )
//        )
//        lis_khaosat.add(
//            dto_menu_app_chitiet("CN01-02", "Chức năng 01 - 02", "")
//        )
//        list.add( dto_menu_app(ma_chucnang = "CN01", ten_chucnang = "Chức năng 01", menu_app_chitiet = lis_khaosat ))
        //endregion khởi tạo menu khảo sát



        //endregion khởi tạo menu dữ liệu test

        MainMenuTabsHomePageScreen(
            navController = navHostController,
            context = current,
            state =  list,
            onEvent = {}
        )
    }
}


