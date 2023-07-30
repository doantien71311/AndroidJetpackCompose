package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.providers.provider_list_dm_nhanvien
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienScreen(
    navController: NavController,
    context : Context,
    state: List<dm_nhanvien_cus>,
    onEvent: (NhanVienEvent) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(


                title = { Text(text = "Nhân viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                        //onEvent(NhanVienEvent.SaveData)

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },

                colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),

            )
        },

        content = {

            val scrollState = rememberLazyListState()
            Column(
                modifier = Modifier.padding(it)
            )
            {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                       .background(Color.Red)
                        //.fillMaxSize()
                    // .padding(it)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    itemsIndexed(
                        items = state,
                    ) { index, item ->

                        NhanVienItemScreen(
                            navController,
                            context,
                            item

                        )
                    }

                }


            }
        }

    )

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NhanVienScreenPreview(
  //  @PreviewParameter(provider_list_dm_nhanvien::class , 1 ) state: List<dm_nhanvien_cus>
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        var list = mutableListOf<dm_nhanvien_cus>()
        list.add(
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
            )
        )
        list.add(
            dm_nhanvien_cus(
                ma_nv = "NV0002",
                ten_nv = "tên nhân viên 002 03265",
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
        list.add(
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

        NhanVienScreen(
            navController = navHostController,
            context = current,
            state =  list,
            onEvent = {}
        )
    }
}