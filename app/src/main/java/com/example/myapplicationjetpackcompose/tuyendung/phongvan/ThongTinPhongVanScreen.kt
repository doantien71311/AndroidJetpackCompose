package com.example.myapplicationjetpackcompose.tuyendung.phongvan

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

import com.google.android.material.color.utilities.MaterialDynamicColors.primary

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import androidx.compose.animation.core.*
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.example.myapplicationjetpackcompose.Destination
import com.example.myapplicationjetpackcompose.LoginViewModel
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienItemsScreen
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ThongTinPhongVanScreen (
    navController: NavController,
    context : Context,
    viewModel: ThongTnPhongVanViewModel = hiltViewModel()
) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "Thông tin phỏng vấn") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                actions = {

                    IconButton(onClick = viewModel::henGio) {
                        Icon(Icons.Rounded.AccountBox, contentDescription = null)
                    }
                    IconButton(onClick = viewModel::henGio2) {
                        Icon(Icons.Rounded.Info, contentDescription = null)
                    }

                }
            )
        },
        content = {
            val scrollState = rememberLazyListState()

            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )  {

                var isLoading by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(key1 = true ) {
                    delay(3000)
                    isLoading = false

                }

                val n = 100
                val para: List<Int> = List(n) { 0 }

                val scrollState = rememberLazyListState()
                LaunchedEffect(Unit) {
                    delay(2000)
//                    if (viewModel.indexUngVien > -1) {
//                        scrollState.scrollToItem(thongTinUngVienViewModel.indexUngVien, n)
//                    }
                }

                Column()  {

                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier
                            .background(Color.Red)
                        // .fillMaxSize()
                        // .verticalScroll(state = rememberScrollState())
                        //.padding(it)
                        ,
                        //verticalArrangement = Arrangement.spacedBy(0.dp)
                        verticalArrangement = Arrangement.Center,
                        //horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        itemsIndexed(
                            items = para
                        ) { index, item ->

                            ThongTinPhongVanScreenItem(
                                navController,
                                context,
                                index
                            )
                        }

                    }

                }



                }



        }

    )



}