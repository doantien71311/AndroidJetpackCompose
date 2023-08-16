package com.example.myapplicationjetpackcompose.mainmenu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.myapplicationjetpackcompose.EnumArgument
import com.example.myapplicationjetpackcompose.EnumDeepLink
import com.example.myapplicationjetpackcompose.HomeScreen
import com.example.myapplicationjetpackcompose.caidat.CaiDatThongBao
import com.example.myapplicationjetpackcompose.dangnhap.DangNhapPage
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienEditItemScreen
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienEditViewModel
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienScreen
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienViewModel
import com.example.myapplicationjetpackcompose.khaosat.tuyendung.KhaoSatTuyenDungB1Screen
import com.example.myapplicationjetpackcompose.khaosat.tuyendung.KhaoSatTuyenDungScreen
import com.example.myapplicationjetpackcompose.services.EnumFirebaseMessagingService
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienScreen
import com.example.myapplicationjetpackcompose.tuyendung.phongvan.ThongTinPhongVanScreen
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {


    lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val coroutineScope = rememberCoroutineScope()

                    navHostController = rememberNavController()
                    NavigationAppHost(navController = navHostController)

//
//                    ModalNavigationDrawer(
//                        drawerContent = { DrawerContent(navHostController, drawerState) },
//                        drawerState = drawerState
//                    ) {

//                        Scaffold(topBar = {
//                            TopAppBar(
//                                title = { Text(text = "Navigation Drawer") },
//                                navigationIcon = {
//
//                                    IconButton(onClick = {
//
//                                        if (drawerState.isClosed) {
//                                            coroutineScope.launch {
//                                                drawerState.open()
//                                            }
//                                        } else {
//                                            coroutineScope.launch {
//                                                drawerState.close()
//                                            }
//                                        }
//
//                                    }) {
//                                        Icon(Icons.Filled.Menu, contentDescription = "Drawer Menu.")
//                                    }
//
//                                })
//                        }) {
//
//                            Box (modifier = Modifier.padding(it)) {
//
//                                NavigationAppHost(navController = navHostController)
//                            }
//                        }


                    //}




                    if (intent.hasExtra(EnumFirebaseMessagingService.ma_chucnang)) {
                        val m_ma_chucnang: String = intent.getStringExtra(
                            EnumFirebaseMessagingService.ma_chucnang
                        ) ?: MainMenuDestination.MAINMENU.route

                        navHostController.navigate(m_ma_chucnang)
                    }


                }
            }


        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavigationAppHost(navController: NavHostController) {


        val context = LocalContext.current


        NavHost(
            navController = navController,
            startDestination = MainMenuDestination.MAINMENU.route

        ) {

            composable(route = MainMenuDestination.LOGIN.route)
            {
                DangNhapPage(navController)
            }

            composable(route = MainMenuDestination.MAINMENU.route)
            {
                MainMenuSrceen(navController, context, intent)
            }


            composable(route = MainMenuDestination.Home.route)
            {
                HomeScreen(navController)
            }


            //region Hành chính nhân sự

            //region Chức vụ
            composable(route = MainMenuDestination.DANHMUC_ChucVu.route)
            {

                KhaoSatTuyenDungScreen(
                    navController, context
                )

            }
            //endregion Chức vụ


            //region Nhân viên
            composable(route = MainMenuDestination.DANHMUC_NhanVien.route)
            {
                val nhanVienViewModel = hiltViewModel<NhanVienViewModel>()
                NhanVienScreen(
                    navController, context,
                    state = nhanVienViewModel.state,
                    onEvent = nhanVienViewModel::onEvent
                )

            }

            composable(route = MainMenuDestination.DANHMUC_NhanVien_Edit.route,
                arguments = listOf(

                    navArgument(EnumArgument.keyvalue) {
                        type = NavType.StringType
                        defaultValue = ""
                    },

                    navArgument(EnumArgument.tungay) {
                        type = NavType.StringType
                        defaultValue = ""
                    },

                    navArgument(EnumArgument.denngay) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                ),
            )
            { backstackEntry ->

                val nhanVienEditViewModel = getNhanVienEditViewModel(
                    pKeyvalue = backstackEntry.arguments?.getString(EnumArgument.keyvalue)?:""
                )

                NhanVienEditItemScreen(
                    navController, context,
                    state = nhanVienEditViewModel.state,
                    onEvent = nhanVienEditViewModel::onEvent
                )

            }

            //endregion Nhân viên

            //region Hành chính nhân sự

            //region Khảo sát
            navigation(
                startDestination = MainMenuDestination.NHAPLIEU_KhaoSat_TuyenDung.route,
                route = MainMenuDestination.NHAPLIEU_KhaoSat.route
            )
            {
                //region Khảo sát tuyển dụng
                composable(
                    route = MainMenuDestination.NHAPLIEU_KhaoSat_TuyenDung.route,
                    deepLinks = (listOf(navDeepLink {
                        uriPattern = EnumDeepLink.MyAppDeepLink
                        action = Intent.ACTION_VIEW
                    }
                    ))
                )
                {
                    KhaoSatTuyenDungScreen(navController, context)
                }
                //endregion Khảo sát tuyển dụng
            }
            //endregion Khảo sát


            navigation(
                startDestination = MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route,
                route = "nested_graph_route"
            )
            {

                //region thông tin ứng viên
                composable(
                    route = MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route,
                    deepLinks = (listOf(navDeepLink {
                        // uriPattern = "myapp://details/"+MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route
                        // uriPattern = "deeplink://details/"+MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route
                        // uriPattern = " https://myapplicationjetpackcompose.page.link/app/"+MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route
                        uriPattern = EnumDeepLink.MyAppDeepLink
                        action = Intent.ACTION_VIEW
                    }
                    ))
                )
                {
                    ThongTinUngVienScreen(navController, context)
                }

                composable(route = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                    arguments = listOf(

                        navArgument(EnumArgument.keyvalue) {
                            type = NavType.StringType
                            defaultValue = ""
                        },

                        navArgument(EnumArgument.tungay) {
                            type = NavType.StringType
                            defaultValue = ""
                        },

                        navArgument(EnumArgument.denngay) {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    ),
                    deepLinks = (listOf(navDeepLink {
                        uriPattern =
                            EnumDeepLink.MyAppDeepLink + MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route
                        action = Intent.ACTION_VIEW
                    }
                    )))
                { backstackEntry ->

                    ThongTinPhongVanScreen(navController
                        , keyvalue = backstackEntry.arguments?.getString(EnumArgument.keyvalue)
                        , tungay = backstackEntry.arguments?.getString(EnumArgument.tungay)
                        , denngay = backstackEntry.arguments?.getString(EnumArgument.denngay)
                    )
                }

                //endregion thông tin ứng viên

                //region Kích hoạt thành viên

                composable(route = MainMenuDestination.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet.route,
                    arguments = listOf(

                      navArgument(EnumArgument.keyvalue) {
                            type = NavType.StringType
                           defaultValue = ""
                      },

                        navArgument(EnumArgument.tungay) {
                            type = NavType.StringType
                            defaultValue = ""
                        },

                        navArgument(EnumArgument.denngay) {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    ),
                    deepLinks = (listOf(navDeepLink {
                        uriPattern =
                            EnumDeepLink.MyAppDeepLink + MainMenuDestination.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet.route
                        action = Intent.ACTION_VIEW
                    }))
                )
                { backstackEntry ->

                    KichHoatThanhVienScreen(navController
                    , keyvalue = backstackEntry.arguments?.getString(EnumArgument.keyvalue)
                        , tungay = backstackEntry.arguments?.getString(EnumArgument.tungay)
                       , denngay = backstackEntry.arguments?.getString(EnumArgument.denngay)
                    )
                }

                //endregion Kích hoạt thành viên


            }


            navigation(
                startDestination = MainMenuDestination.CAIDAT_THONGBAO.route,
                route = MainMenuDestination.CAIDAT.route
            )
            {
                composable(
                    route = MainMenuDestination.CAIDAT_THONGBAO.route,
                )
                {
                    CaiDatThongBao(navController)
                }
            }



        }
    }

}







