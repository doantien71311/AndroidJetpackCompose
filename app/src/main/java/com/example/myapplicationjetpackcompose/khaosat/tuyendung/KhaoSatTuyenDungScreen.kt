package com.example.myapplicationjetpackcompose.khaosat.tuyendung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienItemScreen
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinNganHangScreen
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinNguoiTuyenDungScreen
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinThanhVienScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun KhaoSatTuyenDungScreen(
    navController: NavController,
    context : Context,

    ) {


    Scaffold(
        topBar = {
            TopAppBar(


                title = { Text(text = "Tuyển dụng") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),

                )
        },

        content = {

            val scrollState = rememberLazyListState()
            val horizontalPagerState = rememberPagerState()
            val verticalPagerState = rememberPagerState()
            val scope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it),

                )
            {


                KhaoSatTuyenDungStepScreenPreview()


                HorizontalPager(
                    pageCount = 3,
                    state = horizontalPagerState
                ) { currentPage ->
                    when (currentPage) {

                        0 -> KhaoSatTuyenDungB1Screen(
                            navController,
                            context,
                            horizontalPagerState,
                            scope
                        )

                        1 -> KhaoSatTuyenDungB2Screen(
                            navController,
                            context,
                            horizontalPagerState,
                            scope
                        )

                        2 -> KhaoSatTuyenDungB3Screen(
                            navController,
                            context,
                            horizontalPagerState,
                            scope
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
fun KhaoSatTuyenDungScreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        val list = dm_nhanvien_cus (
            ma_nv = "NV0001",
            ten_nv = "tên nhân viên 001",
            hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
            ma_chucvu = "AGG",
            ten_chucvu = "Chuyên viên tư vấn cấp cao lắm luôn đó nha nha",
            ma_chinhanh = "CN0001",
            ten_chinhanh = "Văn phòng 00001 khu vực đông nam bộ",
            dienthoai_lienhe = "0846356995",
            email = "buivantienthĩ@gmail.com",
            ngaysinh = LocalDateTimeGetNow(),
            ngayvaolam = LocalDateTimeGetNow(),

            )
        KhaoSatTuyenDungScreen(
            navController = navHostController,
            context = current,

        )
    }
}