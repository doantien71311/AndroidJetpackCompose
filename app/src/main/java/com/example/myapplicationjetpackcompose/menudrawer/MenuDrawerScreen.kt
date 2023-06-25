package com.example.myapplicationjetpackcompose.menudrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.example.myapplicationjetpackcompose.R
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        label = {
            Text(text = "Setting")
                },
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

