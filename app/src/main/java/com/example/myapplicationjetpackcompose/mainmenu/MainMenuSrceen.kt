package com.example.myapplicationjetpackcompose.mainmenu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.menudrawer.MenuDrawerSrceenContent
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
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


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                }) {

                    Box(modifier = Modifier.padding(it)) {

                        LazyColumn(
                            //  modifier = Modifier.verticalScroll(rememberScrollState())
                        )

                        {

                            itemsIndexed(
                                items = mainMenuViewModel.ListMenuApp.toTypedArray()

                            ) { index, item ->

                                Text(
                                    text = item.ten_chucnang ?: "",
                                    //  modifier = Modifier.align(Alignment.CenterHorizontally)

                                )
                                RowsCarMenuSrceen(
                                    navController,
                                    context,
                                    item.menu_app_chitiet!!.toTypedArray()
                                )

                            }
                        }

                    }
                }


            }
        }
    )


}



@Composable
fun RowsCarMenuSrceen(
    navController: NavController,
    context : Context,
    para: Array<dto_menu_app_chitiet>,
) {

    LazyRow (
      // modifier = Modifier.horizontalScroll(rememberScrollState()),

    ) {
        itemsIndexed (
           items =  para
        )  { index, item ->

            MainMenuItemsScreen(navController,
                context,
                item,
            )

        }
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

