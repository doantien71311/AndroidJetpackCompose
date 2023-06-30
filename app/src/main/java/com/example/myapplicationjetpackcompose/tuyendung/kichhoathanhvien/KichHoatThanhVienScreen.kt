package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

import android.annotation.SuppressLint
import android.provider.Settings.Global
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.OnlinePrediction
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuScreen
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.tuyendung.phongvan.ThongTinPhongVanScreenItem
import com.google.type.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun KichHoatThanhVienScreen (
    navController: NavController,
    viewModel: KichHoatThanhVienViewModel = hiltViewModel(),
    keyvalue : String? = "",
    tungay: String? = "",
    denngay: String? = "",
        )

{

    val dm_ungvien_cus : dm_ungvien_cus by viewModel.dm_ungvien_cus.observeAsState(initial = dm_ungvien_cus())

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "Kích hoạt thành viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                      containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        content = {


            val horizontalPagerState = rememberPagerState()
            val verticalPagerState = rememberPagerState()
            val scope = rememberCoroutineScope()



            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        ,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {

                    HorizontalPager(
                        pageCount = 3,
                        state = horizontalPagerState
                    ) { currentPage ->
                        when (currentPage) {

                            0 -> ThongTinNguoiTuyenDungScreen(horizontalPagerState, scope)
                            1 -> ThongTinThanhVienScreen(horizontalPagerState, scope)
                            2 -> ThongTinNganHangScreen(
                                horizontalPagerState,
                                scope,
                                viewModel,
                                dm_ungvien_cus
                            )

                        }
                    }

                }

                // ThongTinNguoiTuyenDungNutScreen(horizontalPagerState, scope)

                val pageItems = listOf<Int>(0,1,2)

                Row(
                    modifier = Modifier

                        .align(
                            alignment = Alignment.BottomCenter
                        )
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                )

                {
                    pageItems.forEachIndexed { index, item ->

                        Canvas(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(10.dp)
                                .clickable {
                                    scope.launch {
                                        horizontalPagerState.scrollToPage(index)
                                    }
                                },
                            onDraw = {
//                                drawCircle(
//                                    color = Color.Green,
//                                    radius = this.size.minDimension / 2.0f
//                                )
                                drawCircle(
                                    color =  if (horizontalPagerState.currentPage==index)
                                        Color.Green
                                        else
                                        Color.LightGray
                                    ,
                                    radius = if (horizontalPagerState.currentPage==index)
                                        this.size.minDimension / 2.0f
                                            else
                                        this.size.minDimension / 3.0f
                                )
                            }
                        )
                    }

                }

            }

        }

    )


}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ThongTinNguoiTuyenDungNutScreen ( horizontalPagerState: PagerState, scope: CoroutineScope)
{
    val pageItems = listOf<Int>(0,1,2)

    Row(
        modifier = Modifier
            .background(Color.Gray)
//            .padding(
//                top = 10.dp
//            )
//            .align(
//                alignment = Alignment.BottomCenter
//            )
            ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    )

    {
        pageItems.forEachIndexed { index, item ->

            Canvas(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .clickable {
                        scope.launch {
                            horizontalPagerState.scrollToPage(index)
                        }
                    },
                onDraw = {
                    drawCircle(
                        color = Color.White,
                        radius = this.size.minDimension / 2.0f
                    )
                    drawCircle(
                        color = Color.Green,
                        radius = this.size.minDimension / 3.0f
                    )
                }
            )
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ThongTinNguoiTuyenDungScreen ( horizontalPagerState: PagerState, scope: CoroutineScope)
{
    val ma_nsd = remember {

        mutableStateOf("")
    }

    Box(modifier = Modifier
        .fillMaxSize()
       // .background(Color.Red)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                //.background(color = Color.Red)
                    ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

           // Spacer(modifier = Modifier.height(10.dp))

            TextField (
                label = { Text(text = "Mã code tuyển dụng") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                singleLine = true,
                maxLines = 1,
                value = ma_nsd.value,
                onValueChange =  { ma_nsd.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null
                    )
                }

            )

           // Spacer(modifier = Modifier.height(5.dp))

            TextField (
                label = { Text(text = "Người tuyển dụng") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                maxLines = 1,
                value = ma_nsd.value,
                onValueChange =  { ma_nsd.value = it },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccessibilityNew,
                        contentDescription = null
                    )
                }

                )


        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ThongTinThanhVienScreen (horizontalPagerState: PagerState, scope: CoroutineScope)
{
    val ma_nsd2 = remember {

        mutableStateOf("")
    }

    Box(modifier = Modifier
        .fillMaxSize()
    //    .background(Color.Green)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            //    .background(color = Color.Green)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Divider()

            Spacer(modifier = Modifier.height(20.dp))

            TextField (
                label = { Text(text = "Tên ứng viên") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                singleLine = true,
                maxLines = 1,
                value = ma_nsd2.value,
                onValueChange =  { ma_nsd2.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccessibilityNew,
                        contentDescription = null
                    )
                }
                )

            Spacer(modifier = Modifier.height(10.dp))

            TextField (
                label = { Text(text = "Điện thoại") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                singleLine = true,
                maxLines = 1,
                value = ma_nsd2.value,
                onValueChange =  { ma_nsd2.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null
                    )
                }
                )

            Spacer(modifier = Modifier.height(20.dp))
            Divider()
            Spacer(modifier = Modifier.height(20.dp))

            TextField (
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                singleLine = true,
                maxLines = 1,
                value = ma_nsd2.value,
                onValueChange =  { ma_nsd2.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AddIcCall,
                        contentDescription = null
                    )
                },
               isError = true


                )
            Spacer(modifier = Modifier.height(20.dp))
            Divider()
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ThongTinNganHangScreen (
    horizontalPagerState: PagerState,
    scope: CoroutineScope,
    viewModel: KichHoatThanhVienViewModel,
    dm_ungvien_cus: dm_ungvien_cus
) {


    Box(
        modifier = Modifier
            .fillMaxSize()

    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

//            TextField(
//                label = { Text(text = "Chức vụ") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        start = 10.dpR
//                        end = 10.dp
//                    ),
//                singleLine = true,
//                maxLines = 1,
//                value = dm_ungvien_cus.vitri_ungtuyen ?:"",
//                onValueChange =  { viewModel.vitri_ungtuyenOnChanged(it) },
//
//                )


            Card (

                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(
                        bottom = 10.dp,
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp

                    )
                    .clip(RoundedCornerShape(0.dp))
                    //.background(color = Color.Blue)
                    .fillMaxWidth()
                    .height(70.dp)
                    .animateContentSize()
                    .clickable {
                        viewModel.lookupChucVu.isShowLookup = true
                    },
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.Green)
                        .fillMaxSize()
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        )
                ) {

                    Text(text = "Chức vụ")
                    Divider()

                    Row (   modifier = Modifier

                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            viewModel.lookupChucVu.isShowLookup = true
                        }
                        ,
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = dm_ungvien_cus.vitri_ungtuyen ?: "",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(50.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.Gray)
                                .padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 5.dp,
                                    top = 13.dp,
                                ),
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,

                        )

                        Text(
                            text = "TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH",
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold

                        )

                    }




                }
            }




            Spacer(modifier = Modifier.height(10.dp))


            TextField(
                label = { Text(text = "Mã code") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                singleLine = true,
                maxLines = 1,
                value = dm_ungvien_cus.ma_uv?:"",
                onValueChange = { viewModel.ma_uvOnChanged(it) },


                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null
                    )
                }
            )


            Spacer(modifier = Modifier.height(10.dp))
//            Button(modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    start = 10.dp,
//                    end = 10.dp
//                ),
//                onClick = {
//
//                    viewModel.lookupChucVu.isShowLookup = true
//
//                }
//            )
//            {
//
//                Text(text = "Xác nhận")
//            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
                onClick = viewModel::kichHoatTaiKhoan
            )
            {

                Text(text = "Kích hoạt")
            }

        }


    }


    if (viewModel.lookupChucVu.isShowLookup ) {
        LookupChucVuScreen(
            lookupChucVuViewModel = viewModel.lookupChucVu,
            onLayDuLieu = viewModel::layDuLieuLookupChucVu

        )
    }

}

