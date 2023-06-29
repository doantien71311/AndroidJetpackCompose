package com.example.myapplicationjetpackcompose.lookup.chucvu

import android.annotation.SuppressLint
import android.os.CancellationSignal.OnCancelListener
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.EnumDefault
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinNganHangScreen
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinNguoiTuyenDungScreen
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ThongTinThanhVienScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookupChucVuScreen (
    lookupChucVuViewModel: LookupChucVuViewModel,
    onLayDuLieu:() -> Unit
)
{

    lookupChucVuViewModel.loadData()

    Dialog(onDismissRequest = {
        lookupChucVuViewModel.isShowLookup = false

    },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),

    )
    {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = { Text(text = "Danh sách chức vụ") },
                    navigationIcon = {
                        IconButton(onClick = {
                            lookupChucVuViewModel.isShowLookup = false

                        }) {
                            Icon(Icons.Default.Clear, "Thoát")
                        }
                    },
                )
            },
            content = {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                )  {

                    LazyColumn(
                        modifier = Modifier
                           // .background(Color.Red)
                            .fillMaxSize()
                            // .verticalScroll(state = rememberScrollState())
                            .padding (

                                top =  20.dp,
                                bottom = 20.dp

                            )
                        ,
                        //verticalArrangement = Arrangement.spacedBy(0.dp)
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        itemsIndexed(
                            items = lookupChucVuViewModel.listdata
                        ) { index, item ->

                            Card (

                                shape = MaterialTheme.shapes.small,
                                modifier = Modifier
                                    .padding(
                                        //bottom = 10.dp,
                                        // top = 10.dp,
                                        // start = 5.dp,
                                        //end = 5.dp

                                    )
                                    .clip(RoundedCornerShape(0.dp))
                                    // .background(color = Color.Blue)
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .animateContentSize()

                                    .clickable {

                                        //Tiến ràng buộc cho thực hiện tuần tự

                                        GlobalScope.launch (Dispatchers.Main) {

                                            lookupChucVuViewModel.rowData = item
                                            //
                                            onLayDuLieu()

                                            //Ẩn poupp
                                            lookupChucVuViewModel.isShowLookup = false
                                        }

                                    },
                            ) {
                                Row (
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                    .fillMaxSize()
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp
                                    )
                                )
                                {

                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.url_hinhanh_app ?: EnumDefault.url_hinhanh_app)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = "ImageRequest example",
                                        modifier = Modifier
                                            .padding(
                                                start = 5.dp,
                                                end = 5.dp
                                            )
                                            //.clip(RoundedCornerShape(20.dp))
                                            .width(50.dp)
                                            .height(50.dp)
                                    )

                                    Column (
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.background)
                                            .fillMaxSize()
                                            .padding(
                                                start = 10.dp,
                                                end = 0.dp
                                            )
                                    ) {

                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(text = item.ma_chucvu ?:"")
                                        Text(text = item.ten_chucvu ?:"")
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Divider()

                                    }
                                }

                            }
                        }

                    }

                }



            }

        )


    }

}