package com.example.myapplicationjetpackcompose

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
            CarMenuSrceen()
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