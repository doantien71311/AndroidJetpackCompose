package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.com.example.mayapplicationjetcompose.data.FakeDataRepoImpl

//@HiltAndroidTest
//@Config(application = HiltTestApplication::class)
@RunWith(JUnit4::class)
class NhanVienTest {

    @get:Rule
    val composeRule = createComposeRule()

   lateinit var nhanVienViewModel: NhanVienViewModel
    lateinit var context: Context
//
    @Before
    fun init() {

        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
        .setMinimumLoggingLevel(Log.DEBUG)
        .setExecutor(SynchronousExecutor())
        .build()

        nhanVienViewModel = NhanVienViewModel (
            FakeDataRepoImpl(context)
        )

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)

    }

    @Test
    fun NhanVienTest_XemNhanVien() {

        lateinit var navHostController: NavHostController

        composeRule.setContent {

            navHostController = rememberNavController()

            //region tạo dữ liệu
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
            //endregion tạo dữ liệu

            NhanVienScreen(
                navController = navHostController,
                context = context,
                state = nhanVienViewModel.state,
                onEvent = nhanVienViewModel::onEvent
            )



        }

    }



}