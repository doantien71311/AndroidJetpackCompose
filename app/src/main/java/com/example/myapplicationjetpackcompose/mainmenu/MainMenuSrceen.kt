package com.example.myapplicationjetpackcompose.mainmenu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.myapplicationjetpackcompose.R
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet

import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DrawerHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "profile pic",
            modifier = Modifier
                .clip(CircleShape)
                .width(150.dp)
                .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Prashant Developer", fontSize = TextUnit(18F, TextUnitType.Sp), color = Color.Black,)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "xyz@gmail.com", fontSize = TextUnit(14F, TextUnitType.Sp), color = Color.Gray)




    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DrawerHeader()
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(10.dp))
        NavigationDrawerItems(navController,drawerState)
        Spacer(modifier = Modifier.weight(1f))


        Text(text = "Android Blog Code", fontSize = 16.sp)


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerItems(navController: NavHostController, drawerState: DrawerState) {

    var scope = rememberCoroutineScope()

    var currentBackStackEntryAsState = navController.currentBackStackEntryAsState()

    var destination = currentBackStackEntryAsState.value?.destination



    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        label = { Text(text = "Home") },
        selected = destination?.route == "HomePage",
        onClick = {
            navController.navigate("HomePage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }

        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(10.dp))
    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Place, "About") },
        label = { Text(text = "About") },
        selected = destination?.route == "AboutPage",

        onClick = {
            navController.navigate("AboutPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
    Spacer(modifier = Modifier.height(10.dp))


    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Settings, "Setting") },

        label = { Text(text = "Setting") },
        selected = destination?.route == "SettingPage",
        onClick = {
            navController.navigate("SettingPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true
            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )

    Spacer(modifier = Modifier.height(10.dp))


    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Settings, "Đăng xuất") },

        label = { Text(text = "Đăng xuất") },
        selected = false,
        onClick = {
            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {

                // mainMenuViewModel.clearDataStore()
                navController.navigate(MainMenuDestination.LOGIN.route)
                scope.launch {
                    drawerState.close()
                }
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )


}

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
    GlobalScope.launch (Dispatchers.Main) {
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

        MainMenuLoadingAnimation (

            isLoadding = mainMenuViewModel.isLoadding,
            //isLoadding = isLoading,
            contentAfterLoading = {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            ModalDrawerSheet {
                                Text("Drawer title", modifier = Modifier.padding(16.dp))
                                Divider()
                                NavigationDrawerItem(
                                    label = { Text(text = "Drawer Item") },
                                    selected = false,
                                    onClick = { /*TODO*/ }
                                )
                                // ...other drawer items

                            }
                        }
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

                    Box (modifier = Modifier.padding(it)) {

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

                                Button(onClick = {



                                    GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {

                                        mainMenuViewModel.clearDataStore()
                                        navController.navigate(MainMenuDestination.LOGIN.route)
                                    }

                                }) {

                                    Text(text = "Đăng xuất")

                                }

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

