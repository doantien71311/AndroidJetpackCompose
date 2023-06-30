package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

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
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.TopAppBarDefaults

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
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuScreen
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ThongTinUngVienScreen (
    navController: NavController,
    context : Context,
    viewModel : ThongTinUngVienViewModel = hiltViewModel()
) {

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
//                    // RowScope here, so these icons will be placed horizontally
//                    IconButton(
//                        onClick = {
//                        navController.navigate(MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route)
//                    }
//                    ) {
//                        Icon(Icons.Rounded.AccountBox, contentDescription = null)
//
//                    }


//                    IconButton(onClick = {  }) {
//                        Icon(Icons.Rounded.ArrowBack, contentDescription = null)
//                    }
//                    IconButton(onClick = {  }) {
//                        Icon(Icons.Rounded.ArrowBack, contentDescription = null)
//                    }

                }
            )
        },
        content = {

            var isLoading by remember {
                mutableStateOf(true)
            }

            LaunchedEffect(key1 = true ) {
                delay(2000)
                isLoading = false

            }

//            val n = 100
//            val para: List<Int> = List(n) { 0 }

            val scrollState = rememberLazyListState()
            LaunchedEffect(Unit) {

                delay(1000)
//                if (viewModel.indexUngVien > -1) {
//                    scrollState.scrollToItem(viewModel.indexUngVien, 4)
//                }

            }

//            AnimatedVisibility(
//                visible = !isLoading,
//                enter = expandVertically(
//                    // Expand from the top.
//                    expandFrom = Alignment.Top,
//                    // Overwrites the default animation with tween
//                    animationSpec = tween(durationMillis = 5000)
//                ) + fadeIn(
//                    // Fade in with the initial alpha of 0.3f.
//                    //initialAlpha = 0.3f,
//                    //initialAlpha = 0.0f,
//                    // Overwrites the default animation with tween
//                    //animationSpec = tween(durationMillis = 20000)
//                ),
//                exit = slideOutVertically() + shrinkVertically() + fadeOut()
//            ) {




            Column(
                modifier = Modifier.padding(it)
            )  {

                Divider()
                Row() {


                    Button(onClick = {

                        viewModel.isShowHenPhongVan = true

                    }) {
                        Text(text = viewModel.soluongPhongVan.toString() + "/" + viewModel.soluongUngVien.toString())
                    }

                    Button(onClick = {

                        viewModel.loadData()

                    }) {
                        Text(text = "Bỏ chọn")
                    }

                }
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

           // }



        }

    )

    if (viewModel.isShowHenPhongVan ) {
        ThongTinUngVienHenPhongVanScreen(
            viewModel = viewModel,

        )
    }



}

@Composable
fun LoadingForm (
    navController: NavController,
    context : Context

) {

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
        delay(1000)
        scrollState.scrollToItem(99, n)
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

                    ShimmerListItem (
                        isLoadding = isLoading,
                        contentAfterLoading = {
//                            ThongTinUngVienItemsScreen(
//                                navController,
//                                context,
//                                index,
//                                dm_ungvien_cus()
//                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

            }

        }







}

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = Color.Magenta,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEach { value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )
            )
        }
    }

}

@Composable
fun ShimmerListItem(
    isLoadding: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier

)
{

    if (isLoadding) {

        Row(modifier = modifier) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .shimerEffect()
            )

        }

    }
    else {

        contentAfterLoading()
    }

}
fun Modifier.shimerEffect(): Modifier = composed {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX,0f),
            end = Offset (startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
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

