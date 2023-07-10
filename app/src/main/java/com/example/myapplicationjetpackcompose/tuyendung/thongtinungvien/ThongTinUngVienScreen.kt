package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.PersonRemove


import androidx.compose.material.icons.outlined.Today
import androidx.compose.material.icons.rounded.PersonAddAlt
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Divider

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState


import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplicationjetpackcompose.ViewModelFactoryProvider
import dagger.hilt.android.EntryPointAccessors


@Composable
fun getThongTinUngVienViewModel(
    pKeyvalue: String = "",
    pTungay: String = "",
    pDenngay: String = "",
)
        : ThongTinUngVienViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    )
        .thongTinUngVienViewModelFactory()

    return viewModel(
        factory = ThongTinUngVienViewModel.providerMainViewModelFactory
            (
            factory,
            pKeyvalue,
            pTungay,
            pDenngay
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ThongTinUngVienScreen (
    navController: NavController,
    context : Context,

    keyvalue : String? = "",
    tungay: String? = "",
    denngay: String? = "",
) {

    var viewModel: ThongTinUngVienViewModel = getThongTinUngVienViewModel(

        pKeyvalue = keyvalue ?: "",
        pTungay = tungay ?: "",
        pDenngay = denngay ?: "",

        )

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val context = LocalContext.current

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 20.dp,
        sheetSwipeEnabled = true,
        sheetContent = {
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .height(128.dp),
//                contentAlignment = Alignment.Center
//            ) {
//
//            }

            Column (
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                OutlinedTextField(
                    label = { Text(text = "Từ ngày") },
                    value = "Example",
                    enabled = false,

                    // readOnly = true,

                    onValueChange = {},

//                    colors = TextFieldDefaults.textFieldColors(
//                        disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
//                        disabledLabelColor =  MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium)
//                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            Toast
                                .makeText(
                                    context,
                                    "Từ ngày",
                                    Toast.LENGTH_LONG
                                )
                                .show()

                        },

                    )


                Spacer(Modifier.height(10.dp))

                TextField(

                    label = { Text(text = "Từ ngày") },
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            Toast
                                .makeText(
                                    context,
                                    "Từ ngày",
                                    Toast.LENGTH_LONG
                                )
                                .show()


                        },
                    value = viewModel.tungay,
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {  },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Today,
                            contentDescription = null
                        )
                    }

                )
                Spacer(Modifier.height(10.dp))

                TextField(

                    label = { Text(text = "Dến ngày") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            Toast
                                .makeText(
                                    context,
                                    "Đến ngày",
                                    Toast.LENGTH_LONG
                                )
                                .show()


                        },
                    value = viewModel.tungay,
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {

                                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Today,
                            contentDescription = null
                        )
                    }

                )

                Spacer(Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                    }
                ) {
                    Text("Tìm kiếm")
                }
            }
        }) { innerPadding ->

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
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    actions = {

                        IconButton(
                            onClick = {

                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }

                            }
                        ) {

                            Icon(Icons.Rounded.Search, contentDescription = null)

                        }


                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier.padding(it)
                ) {

                ButtonThongTinUngVienScreen(viewModel)

                ThongTinUngVienScreen(
                    navController,
                    context,
                    viewModel
                )
                }

            }

        )
    }

    if (viewModel.isShowHenPhongVan) {
        ThongTinUngVienHenPhongVanScreen(
            viewModel = viewModel,
        )
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ThongTinUngVienScreen (
    navController: NavController,
    context : Context,
    viewModel: ThongTinUngVienViewModel
) {

    Column(
       // modifier = Modifier.padding(it)
    ) {

        DanhSachThongTinUngVienScreen(
            navController,
            context,
            viewModel
        )
        //
    }



}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ButtonThongTinUngVienScreen (
    viewModel: ThongTinUngVienViewModel
) {
   // Spacer(modifier = Modifier.height(10.dp))
    Divider()
    //Spacer(modifier = Modifier.height(10.dp))
    Row (
            modifier = Modifier

                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically

    ) {



            Button(
                onClick = {

                    viewModel.isShowHenPhongVan = true

                },
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight(),


            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAddAlt,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(
                            end = 5.dp
                        ),
                    contentDescription = "drawable icons",

                )

                Column() {

                    Text(text = "Đã chọn ${viewModel.soluongPhongVan.toString()}/${viewModel.soluongUngVien.toString()}")

                }
            }






        Button(onClick = {

            viewModel.loadData()

        },
            modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                modifier = Modifier
                    .size(30.dp)
                    .padding(
                        end = 5.dp
                    ),
                contentDescription = "drawable icons",

                )


            Text(text = "Bỏ chọn")
        }

    }
    //Spacer(modifier = Modifier.height(10.dp))
    Divider()

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DanhSachThongTinUngVienScreen (
    navController: NavController,
    context : Context,
    viewModel: ThongTinUngVienViewModel
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
//        modifier = Modifier
//            .background(Color.Red)
//        ,
        verticalArrangement = Arrangement.Center,

        ) {

        itemsIndexed(
            items = viewModel.listUngvien
        ) { index, item ->

            ThongTinUngVienItemsScreen(
                navController,
                context,
                0,
                viewModel,
                item

            )
        }

    }

}


@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
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

