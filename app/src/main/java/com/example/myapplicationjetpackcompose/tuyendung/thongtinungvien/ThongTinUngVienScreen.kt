package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuItemsScreen
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuViewModel
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThongTinUngVienScreen (
    navController: NavController,
    context : Context
) {

   // val m_ThongTinUngVienViewModel : ThongTinUngVienViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Thông tin ứng viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
            )
        },
        content = {

            val n = 100
            val para: List<Int> = List(n) { 0 }


            LazyColumn(

                modifier = Modifier.background(Color.Red)
                    .fillMaxSize()
                    //.verticalScroll(state = rememberScrollState())
                    .padding(it),
                //verticalArrangement = Arrangement.spacedBy(0.dp)
                verticalArrangement = Arrangement.Center,
                //horizontalAlignment = Alignment.CenterHorizontally

            ) {


                    itemsIndexed (
                        items =  para
                    )  { index, item ->

                        ThongTinUngVienItemsScreen(
                            navController,
                            context
                        )

                    }


            }
        }

    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ThongTinUngVienScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        NavigationAppHost(navController = navHostController)

        ThongTinUngVienScreen (navHostController, current)
    }
}

