package com.example.myapplicationjetpackcompose.mainmenu


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.QrCode2

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.mainmenu.homepage.MainMenuTabsHomePageItemSreen
import com.example.myapplicationjetpackcompose.mainmenu.homepage.MainMenuTabsHomePageScreen
import com.example.myapplicationjetpackcompose.mainmenu.qrcodegetlink.MainMenuTabsQRcodeGetLinkItem

import com.example.myapplicationjetpackcompose.menudrawer.MenuDrawerSrceenContent
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainMenuSrceen (
    navController: NavController,
    context: Context,
    intent: Intent,
    mainMenuViewModel : MainMenuViewModel = hiltViewModel()
) {

    //Tiến ràng buộc khởi tạo MainMenuViewModel trước
    GlobalScope.launch(Dispatchers.Main) {
        val isAuth = mainMenuViewModel.isAuth.first()
        if (!isAuth) {

            navController.navigate(MainMenuDestination.LOGIN.route)

        }
    }



//    var isLoading by remember {
//        mutableStateOf(true)
//    }

//    LaunchedEffect(key1 = true ) {
//        delay(5000)
//        isLoading = false
//    }

    val horizontalPagerState = rememberPagerState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    HorizontalPager(
        pageCount = 5,
        state = horizontalPagerState
    ) { currentPage ->
        when (currentPage) {

            0 -> MainMenuTabsHomePageScreen(navController= navController,
                context = context,
                state = mainMenuViewModel.ListMenuApp,
                onEvent = mainMenuViewModel::onEvent)
            1 -> MainMenuTabsHomeScreen(context, horizontalPagerState, scope)
            2 -> MainMenuTabsQRcodeGetLinkItem()


        }
    }

    MainMenuLoadingAnimation(
        isLoadding = mainMenuViewModel.isLoadding,
        //isLoadding = isLoading,
        contentAfterLoading = {

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    MenuDrawerSrceenContent(navController, drawerState)
                },
            ) {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text(text = "Main menu") },
                        navigationIcon = {

                            IconButton(onClick = {

                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }

                        }

                    )

                },
                    bottomBar = {
                        MainMenuTabsScreen(
                            context = context,
                            horizontalPagerState = horizontalPagerState,
                            scope = scope
                        )
                    }

                ) {

                    Box(modifier = Modifier.padding(it)) {


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                            ,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {



                            HorizontalPager(
                                pageCount = 5,
                                state = horizontalPagerState
                            ) { currentPage ->
                                when (currentPage) {

                                    0 -> MainMenuTabsHomePageScreen(navController= navController,
                                        context = context,
                                        state = mainMenuViewModel.ListMenuApp,
                                        onEvent = mainMenuViewModel::onEvent)
                                    1 -> MainMenuTabsHomeScreen(context, horizontalPagerState, scope)
                                    2 -> MainMenuTabsQRcodeGetLinkItem()


                                }
                            }



                        }




                    }
                }


            }
        }
    )


}

//@Composable
//fun RowsCarMenuSrceen(
//    navController: NavController,
//    context : Context,
//    para: Array<dto_menu_app_chitiet>,
//) {
//
//    LazyRow (
//        // modifier = Modifier.horizontalScroll(rememberScrollState()),
//
//    ) {
//        itemsIndexed (
//            items =  para
//        )  { index, item ->
//
//            MainMenuTabsHomePageItemSreen(navController,
//                context,
//                item,
//            )
//
//        }
//    }
//
//}
//
//@Composable
//fun MainMenuTabSrceen(
//    navController: NavController,
//    context : Context,
//    mainMenuViewModel: MainMenuViewModel
//) {
//    LazyColumn(
//        //  modifier = Modifier.verticalScroll(rememberScrollState())
//    )
//
//    {
//
//        itemsIndexed(
//            items = mainMenuViewModel.ListMenuApp.toTypedArray()
//
//        ) { index, item ->
//
//            Text(
//                text = item.ten_chucnang ?: "",
//                //  modifier = Modifier.align(Alignment.CenterHorizontally)
//
//            )
//            RowsCarMenuSrceen(
//                navController,
//                context,
//                item.menu_app_chitiet!!.toTypedArray()
//            )
//
//        }
//
//
//    }
//
//
//}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainMenuTabsScreen(
    context : Context,
    horizontalPagerState: PagerState,
    scope: CoroutineScope
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colorScheme.outlineVariant),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        MainMenuTabsHomeScreen(
            context = context,
            horizontalPagerState = horizontalPagerState,
            scope = scope
        )

        Text (text = "Home 2")

        MainMenuTabsQrCodeScreen (
            context = context,
            horizontalPagerState = horizontalPagerState,
            scope = scope
        )


        Text (text = "Thông báo")
        Text (text = "Cài đặt")

    }



}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainMenuTabsHomeScreen(
    context : Context,
    horizontalPagerState: PagerState,
    scope: CoroutineScope
) {

    Column(
        modifier = Modifier
            .clickable {

//                Toast.makeText(
//                    context,
//                    "Kích hoạt thành công",
//                    Toast.LENGTH_LONG
//                ).show()

                scope.launch {
                    horizontalPagerState.scrollToPage(0)
                }
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Icon(Icons.Default.Home, "")
        Text(text = "Trang chủ")
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainMenuTabsQrCodeScreen(
    context: Context,
    horizontalPagerState: PagerState,
    scope: CoroutineScope

) {

    Column (
        modifier = Modifier
            .clickable {

                scope.launch {
                    horizontalPagerState.scrollToPage(2)
                }

            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Icon(Icons.Default.QrCode2, "")
        Text(text = "QR Profile")
    }


}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuTabsScreenreview() {
    MyApplicationJetpackComposeTheme {

        val context = LocalContext.current
//        MainMenuTabsScreen(
//            context = context
//        )

    }
}


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun CarMenuSrceenPreview() {
//    MyApplicationJetpackComposeTheme {
//
//      //  Header()
//    }
//}

