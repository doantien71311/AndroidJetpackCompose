package com.example.myapplicationjetpackcompose.mainmenu

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.myapplicationjetpackcompose.EnumChannel
import com.example.myapplicationjetpackcompose.EnumDeepLink
import com.example.myapplicationjetpackcompose.HomeScreen
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
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {



    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                     navHostController = rememberNavController()
                     NavigationAppHost(navController = navHostController)

//                   //  CarMenuSrceen(navHostController, current)
//
                    // Get token
                    // [START log_reg_token]
                    Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }

                        // Get new FCM registraddtion token
                        val token = task.result
                        Log.w("TAG", "ToKenPCM: " +token)

                    })

                    FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Installations", "Installation ID: " + task.result)
                        } else {
                            Log.e("Installations", "Unable to get Installation ID")
                        }
                    }

                    if (intent.hasExtra(EnumFirebaseMessagingService.ma_chucnang)) {

                        val m_ma_chucnang : String = intent.getStringExtra(
                            EnumFirebaseMessagingService.ma_chucnang)!!
                        navHostController.navigate(m_ma_chucnang)
                    }

//
//                    //Tạo chanel thông báo
//                    val channel = NotificationChannel(EnumChannel.MainChannelId,
//                        EnumChannel.MainChannelName,
//                        NotificationManager.IMPORTANCE_DEFAULT)
//                    channel.description= "Thông báo"
//                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                    notificationManager.createNotificationChannel(channel)



                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navHostController.handleDeepLink(intent)
    }
    @Composable
    fun NavigationAppHost(navController: NavHostController) {

        val context = LocalContext.current

        NavHost(navController = navController ,
                    startDestination = MainMenuDestination.MAINMENU.route
        ) {

            composable(route = MainMenuDestination.MAINMENU.route)
            {
                CarMenuSrceen(navController, context, intent)
            }


            composable(route = MainMenuDestination.Home.route)
            {
                HomeScreen(navController)
            }


            composable(route = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                    deepLinks = ( listOf(navDeepLink {
                uriPattern = EnumDeepLink.MyAppDeepLink + MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route
                action = Intent.ACTION_VIEW
            }
            )))
            {
                ThongTinPhongVanScreen(navController,context)
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
                deepLinks = ( listOf(navDeepLink {
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