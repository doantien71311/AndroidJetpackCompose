package com.example.myapplicationjetpackcompose.mainmenu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.HomeScreen
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination (val route: String)
{
    object  MAINMENU: Destination ("MAINMENU")
    object  DANHMUC_NhanVien: Destination ("DANHMUC_NhanVien")
    object  Home: Destination ("home")

}

@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {

   //val m_MainMenuViewModel: MainMenuViewModel by viewModels()

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
                     val current = LocalContext.current

                     NavigationAppHost(navController = navHostController)

                   //  CarMenuSrceen(navHostController, current)

                    // Get token
                    // [START log_reg_token]
                    Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }

                        // Get new FCM registraddtion token
                        val token = task.result

                    })


                }
            }
        }
    }

    @Composable
    fun NavigationAppHost(navController: NavHostController) {

        val context = LocalContext.current

        NavHost(navController = navController , startDestination = Destination.MAINMENU.route ) {

            composable(route = Destination.MAINMENU.route)
            {
                CarMenuSrceen(navController, context, intent)
            }


            composable(route = com.example.myapplicationjetpackcompose.Destination.Home.route)
            {
                HomeScreen(navController)
            }

            composable(route = com.example.myapplicationjetpackcompose.mainmenu.Destination.DANHMUC_NhanVien.route)
            {
                NhanVienScreen(navController, context)
            }

        }
    }


}