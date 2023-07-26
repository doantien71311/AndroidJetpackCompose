package com.example.myapplicationjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienScreen
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienViewModel
import com.example.myapplicationjetpackcompose.services.DataStoreServices
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.tuyendung.phongvan.ThongTinPhongVanTestScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//class HiltPreviewActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationJetpackComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}

@AndroidEntryPoint
class HiltPreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

           // GreetingPreview2()

            // ThongTinPhongVanTestScreenPreview()

           // NhanVienScreenPreview()

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    MyApplicationJetpackComposeTheme {
//        Greeting("Android")
//    }
//}

//@Preview(showBackground = true)
////@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ThongTinPhongVanTestScreenPreview() {
//
//    lateinit var navHostController: NavHostController
//
//    MyApplicationJetpackComposeTheme {
//
//        navHostController = rememberNavController()
//        // NavigationAppHost(navController = navHostController)
//
//        val current = LocalContext.current
//
//        ThongTinPhongVanTestScreen ()
//    }
//}

@SuppressLint("CoroutineCreationDuringComposition")
@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NhanVienScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        // NavigationAppHost(navController = navHostController)

        val current = LocalContext.current


//        val dataStoreServies : IDataStoreServies = DataStoreServices(current)
//
//        GlobalScope.launch(Dispatchers.Main) {
//
//            dataStoreServies.saveKeyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiI4NWFhM2RjYi1iN2MwLTQ5ZGEtOGVmMy1iZTdhMDg4NzFjOWYiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0.BnSRZsaSUdxMPMyBLRAhpjCO7hTREd9m1M9KweQv0p0")
//            dataStoreServies.saveUserName("ADMIN")
//            dataStoreServies.saveMaNV("ADMIN")
//        }

//
//        viewModelScope.launch {
//            if (login_enable) {
//                dataStoreServies.saveUserName(_ht_dm_nsd.ma_nsd!!)
//                dataStoreServies.savePassWord(_ht_dm_nsd.matkhau!!)
//                dataStoreServies.saveMaNV(_ht_dm_nsd.ma_nsd!!)
//            }
//        }

     //   val nhanVienViewModel : NhanVienViewModel() = hiltViewModel()

//        NhanVienScreen(
//            navController = navHostController,
//            context = current,
//            viewModel = nhanVienViewModel,
//
//        )
    }
}