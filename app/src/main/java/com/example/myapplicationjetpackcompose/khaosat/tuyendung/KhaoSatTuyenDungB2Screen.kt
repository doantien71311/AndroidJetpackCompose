package com.example.myapplicationjetpackcompose.khaosat.tuyendung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.widget.Space
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuTabsScreen
import com.example.myapplicationjetpackcompose.mainmenu.homepage.MainMenuTabsHomePageColumnSrceen
import com.example.myapplicationjetpackcompose.model.dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun KhaoSatTuyenDungB2Screen(
    navController: NavController,
    context : Context,
    horizontalPagerState: PagerState,
    scope: CoroutineScope,
    listChucvu: List<dm_chucvu_ds>

) {

    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
        // .fillMaxHeight()
        ,
        // verticalArrangement = Arrangement.SpaceBetween
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "BẠN MONG MUỐN VỊ TRÍ?",
            modifier = Modifier
                .padding(
                    0.dp
                ),
        )

        Spacer(modifier = Modifier.height(10.dp))

        //region hiện thị hình ảnh
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://daiichitheworldlink-hinhanh.theworldlink.vn/TheWorldLink/WebCty/Images/khaosatmaster/tuyendung/02_vi-tri-ung-tuyen.png")
                .crossfade(true)
                .build(),
            contentDescription = "ImageRequest example",
            modifier = Modifier
                .height(350.dp)
                .width(350.dp)
                .background(Color.Blue)
                .padding(0.dp)
        )

        //endregion

        Spacer(modifier = Modifier.height(10.dp))

        //region chọn chức vụ
//        Column () {
//            listChucvu.forEach { item ->
//                KhaoSatTuyenDungB2ChucVuItemScreen(
//                    navController,
//                    context,
//                    item
//                )
//            }
//        }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    // .verticalScroll(rememberScrollState())
                    .background(Color.Cyan)
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                    ),
                verticalArrangement = Arrangement.SpaceBetween

            )

            {

                itemsIndexed(
                    items = listChucvu.toTypedArray()

                ) { index, item ->


                    KhaoSatTuyenDungB2ChucVuItemScreen(
                        navController,
                        context,
                        item
                    )

                }

            }



        //endregion chọn chức vụ


        //region cách nút thao tác
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //region nút quay lại của bước 2
            Button(

                modifier = Modifier

                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    //Quay lại bước 1
                    scope.launch {
                        horizontalPagerState.scrollToPage(1)
                    }
                },
                content = {

                    Text("Quay lại")


                }
            )
            //endregion nút quay lại của bước 2

            //region nút tiếp theo của bước 2
            Button(

                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    //Đi Tới bước 3
                    scope.launch {
                        horizontalPagerState.scrollToPage(3)
                    }

                },
                content = {

                    Text("Tiếp theo")


                }
            )
            //endregion nút tiếp theo của bước 2
        }
        //endregion các nút thao tác
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KhaoSatTuyenDungB2ScreenPreview(
) {

    lateinit var navController: NavHostController

    MyApplicationJetpackComposeTheme {

        navController = rememberNavController()
        val current = LocalContext.current
        val horizontalPagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        val listChucvu = mutableListOf<dm_chucvu_ds>()

        listChucvu.add(
            dm_chucvu_ds(ma_chucvu = "CTV", ten_chucvu = "Cộng tác viên")
        )

        listChucvu.add(
            dm_chucvu_ds(ma_chucvu = "FC", ten_chucvu = "Tu vấn tài chính")
        )
        listChucvu.add(
            dm_chucvu_ds(ma_chucvu = "UM", ten_chucvu = "Trưởng nhóm kinh doanh")
        )
        listChucvu.add(
            dm_chucvu_ds(ma_chucvu = "BM", ten_chucvu = "Trưởng phòng")
        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "SUM", ten_chucvu = "Trưởng nhóm cấp cao")
//        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "SBM", ten_chucvu = "Trưởng phòng cấp cao")
//        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "GAD", ten_chucvu = "Giám đốc tổng đại lý")
//        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "GAD", ten_chucvu = "Giám đốc tổng đại lý")
//        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "GAD", ten_chucvu = "Giám đốc tổng đại lý")
//        )
//        listChucvu.add(
//            dm_chucvu_ds(ma_chucvu = "GAD", ten_chucvu = "Giám đốc tổng đại lý")
//        )

        Scaffold(
            topBar = {
                TopAppBar(


                    title = { Text(text = "Tuyển dụng") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Rounded.ArrowBack, "Back")
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),

                    )
            },
            content = {

                KhaoSatTuyenDungB2Screen(
                    navController = navController,
                    context = current,
                    horizontalPagerState = horizontalPagerState,
                    scope =  scope,
                    listChucvu = listChucvu,
                )
            }
        )


    }
}