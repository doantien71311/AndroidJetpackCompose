package com.example.myapplicationjetpackcompose

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme


sealed class Destination (val route: String)
{
    object  Home: Destination ("home")
    object  Profile: Destination("profile")
    object  CarMenu: Destination("carmenu")
    object  List: Destination("List")
    object  Detail: Destination("detail/{elementId}") {
            fun createRoute (elementId: Int) = "detail/$elementId"

    }

    object  Login: Destination("login")

}

class MainActivity : ComponentActivity() {

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
                }
            }
        }
    }
}

@Composable
fun NavigationAppHost(navController: NavHostController) {

    val ctx = LocalContext.current

    NavHost(navController = navController , startDestination = Destination.Home.route ) {
        composable(route = Destination.Login.route)
        {
            ctx.startActivity(Intent(ctx, LoginActivity::class.java))
        }

        composable(route = Destination.Home.route)
        {
            HomeScreen(navController)
        }

        composable(route = Destination.List.route)
        {
            ListScreen(navController)
        }

        composable(route = Destination.CarMenu.route)
        {
            var url_hinhanh = "https://daiichitheworldlink-hinhanh.theworldlink.vn/TheWorldLink/WebPortal/Images/logo.png"
            var lis = mutableListOf<dto_menu_app>()

            var lis_ct = mutableListOf<dto_menu_app_chitiet>()
            lis_ct.add(
                dto_menu_app_chitiet("CN01-01", "Chức năng 01 - 01", url_hinhanh)
            )
            lis_ct.add(
                dto_menu_app_chitiet("CN01-02", "Chức năng 01 - 02", url_hinhanh)
            )

            lis.add( dto_menu_app(ma_chucnang = "CN01", ten_chucnang = "Chức năng 01", menu_app_chitiet = lis_ct ))

            var lis_ct2 = mutableListOf<dto_menu_app_chitiet>()
            lis_ct2.add(
                dto_menu_app_chitiet("CN02-01", "Chức năng 02 - 01", url_hinhanh)
            )
            lis_ct2.add(
                dto_menu_app_chitiet("CN02-02", "Chức năng 02 - 02", url_hinhanh)
            )

            lis.add( dto_menu_app(ma_chucnang = "CN02", ten_chucnang = "Chức năng 02", menu_app_chitiet = lis_ct2 ))

            var lis_ct3 = mutableListOf<dto_menu_app_chitiet>()
            lis_ct3.add(
                dto_menu_app_chitiet("CN02-01", "Chức năng 02 - 01", url_hinhanh)
            )
            lis_ct3.add(
                dto_menu_app_chitiet("CN02-02", "Chức năng 02 - 02", url_hinhanh)
            )

            lis.add( dto_menu_app(ma_chucnang = "CN03", ten_chucnang = "Chức năng 03", menu_app_chitiet = lis_ct3 ))

            CarMenuSrceen(lis.toTypedArray())
        }

        composable(route = Destination.Detail.route)
        {
                navBackStackEntry ->
            
            val elementID = navBackStackEntry.arguments?.getInt("elementID")
            if (elementID == null) {
                Toast.makeText(ctx, "ElementID is required", Toast.LENGTH_LONG).show()
            } else {

                DetailScreen(elementId = elementID)
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationJetpackComposeTheme {
      //  Greeting("Android")
    }
}