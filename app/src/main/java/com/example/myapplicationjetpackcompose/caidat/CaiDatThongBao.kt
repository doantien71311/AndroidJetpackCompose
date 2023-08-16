package com.example.myapplicationjetpackcompose.caidat

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienItemsScreen
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaiDatThongBao(
    navController: NavHostController,
    viewModel : CaiDatThongBaoViewModel = hiltViewModel()
) {

//    var hasNotificationPermission by remember {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            mutableStateOf(
//                ContextCompat.checkSelfPermission(
//                    LocalContext,
//                    android.Manifest.permission.POST_NOTIFICATIONS
//                ) == PackageManager.PERMISSION_GRANTED
//            )
//        } else mutableStateOf(true)
//    }

    val thongbao_tuyendung : Boolean by viewModel.thongbao_tuyendung.observeAsState(initial = false)
    val thongbao_doinhom : Boolean by viewModel.thongbao_doinhom.observeAsState(initial = false)
    val thongbao_kiemtra : Boolean by viewModel.thongbao_kiemtra.observeAsState(initial = false)

    var hasNotificationPermission : Boolean = false

    val launcher  = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGanted ->
            hasNotificationPermission  = isGanted
            viewModel.thongbao_kiemtraOnChange(isGanted)

        }

    )



    var checked by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(true) }




    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "Cài đặt thông báo") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MainMenuDestination.MAINMENU.route)

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                actions = {
//                    // RowScope here, so these icons will be placed horizontally
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(Icons.Rounded.AccountBox, contentDescription = null)
//                    }

                    Switch(
                        modifier = Modifier.semantics { contentDescription = "Demo" },
                        checked = thongbao_kiemtra,
                        onCheckedChange = {
                            viewModel.thongbao_kiemtraOnChange(it)
                            if (thongbao_kiemtra) {
                                launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                            }

                        }
                    )
//

                }
            )
        },
        content = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(it)
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {




                Divider()
                Row () {

                    Text(
                        text = "Nhận thông báo từ thành viên",
                        textAlign = TextAlign.Left
                    )
                    Switch(
                        modifier = Modifier.semantics { contentDescription = "Demo" },
                        checked = checked2,
                        onCheckedChange = { checked2 = it })

                }



            }
        }

    )


}


@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CaiDatThongBaoPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

       // NavigationAppHost(navController = navHostController)

        CaiDatThongBao (navHostController)
    }
}

