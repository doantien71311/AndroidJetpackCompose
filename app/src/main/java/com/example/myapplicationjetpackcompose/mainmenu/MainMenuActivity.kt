package com.example.myapplicationjetpackcompose.mainmenu

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import com.example.myapplicationjetpackcompose.EnumDeepLink
import com.example.myapplicationjetpackcompose.HomeScreen
import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.dangnhap.DangNhapPage
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienScreen
import com.example.myapplicationjetpackcompose.services.EnumFirebaseMessagingService
import com.example.myapplicationjetpackcompose.tuyendung.phongvan.ThongTinPhongVanScreen
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch




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


                    // Get token
                    // [START log_reg_token]
                    Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }

                        // Get new FCM registraddtion token
                        val token = task.result
                        Log.w("TAG", "ToKenPCM: " + token)

                    })

                    FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Installations", "Installation ID: " + task.result)
                        } else {
                            Log.e("Installations", "Unable to get Installation ID")
                        }
                    }

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


            composable(route = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                deepLinks = (listOf(navDeepLink {
                    uriPattern =
                        EnumDeepLink.MyAppDeepLink + MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route
                    action = Intent.ACTION_VIEW
                }
                )))
            {
                ThongTinPhongVanScreen(navController, context)
            }


            composable(route = MainMenuDestination.DANHMUC_NhanVien.route)
            {
                NhanVienScreen(navController, context)
            }

            navigation(
                startDestination = MainMenuDestination.NHAPLIEU_NhanSu_DonDangKyThanhVien_Duyet.route,
                route = "nested_graph_route"
            )
            {
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
            }

        }
    }

}







