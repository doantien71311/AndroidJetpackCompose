package com.example.myapplicationjetpackcompose.menudrawer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawerSrceenContent(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: MenuDrawerSrceenViewModel = hiltViewModel()
) {

    ModalDrawerSheet {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://daiichitheworldlink-hinhanh.theworldlink.vn/TheWorldLink/WebPortal/Images/logo.png")
                .crossfade(true)
                .build(),
            contentDescription = "ImageRequest example",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)

        )


        Divider()
        Text("Tên nhân viên", modifier = Modifier.padding(16.dp))


        Divider()
        NavigationDrawerItem (
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text(text = "Cài đặt") },
            selected = false,
            onClick = {


            }
        )


        Divider()
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Notifications, null) },
            label = { Text(text = "Cài đặt thông báo") },
            selected = false,
            onClick = {

                navController.navigate(MainMenuDestination.CAIDAT_THONGBAO.route)
            }
        )


        Divider()
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Refresh, null) },
            label = { Text(text = "Đổi mật khẩu") },
            selected = false,
            onClick = {


            }
        )


        Divider()
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.ExitToApp, null) },
            label = { Text(text = "Đăng xuất") },
            selected = false,
            onClick = {

                GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {

                    viewModel.clearDataStore()
                    navController.navigate(MainMenuDestination.LOGIN.route)
                }

            }
        )



    }
}

