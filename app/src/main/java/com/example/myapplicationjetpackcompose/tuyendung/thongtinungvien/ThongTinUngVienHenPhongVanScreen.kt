package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LocalPostOffice
import androidx.compose.material.icons.outlined.OnlinePrediction
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun ThongTinUngVienHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
) {

    Dialog(onDismissRequest = {
        viewModel.isShowHenPhongVan = false
    },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),

        )
    {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = { Text(text = "Hẹn phỏng vấn") },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.isShowHenPhongVan = false

                        }) {
                            Icon(Icons.Default.Clear, "Thoát")
                        }
                    },
                )
            },
            content = {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .padding(it)
                )
                {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                )
                {

                    UngVienDanhSachEmailhHenPhongVanScreen(viewModel)

                    FormHenPhongVanScreen(viewModel)


                }



            }
            }

        )


    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun UngVienDanhSachEmailhHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
)
{
    Row (

        modifier = Modifier
            .padding(
                //bottom = 10.dp,
                // top = 10.dp,
                // start = 5.dp,
                // end = 5.dp,
               // it

            )
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Red)
//                        .width(500.dp)
//                        .height(500.dp)


    ) {
        LazyRow(
            // modifier = Modifier.horizontalScroll(rememberScrollState()),

        ) {
            itemsIndexed(
                items = viewModel.listPhongVan
            ) { index, item ->

                Card(

                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .padding(
                            //bottom = 10.dp,
                            // top = 10.dp,
                            start = 5.dp,
                            end = 5.dp

                        )
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color.Blue)
                        .width(100.dp)
                        .height(100.dp)
                        .animateContentSize()

                )
                {

                    Column (
                        modifier = Modifier
//                                        .padding(
//                                            it
//                                        )
                            //.width(100.dp)
                            //.height(50.dp)
                            .fillMaxSize()
                            .background(color = Color.Green)
                        // .align(Alignment.CenterHorizontally)

                    ) {
                        Text(text = item.email ?: "")
                    }
                }

            }
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun FormHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
){
    val diadiem_henphongvan_value : String by viewModel.diadiem_henphongvan.observeAsState(initial = "")
    val link_phongvan_online_value : String by viewModel.link_phongvan_online.observeAsState(initial = "")
    val is_phongvan_online_value : Boolean by viewModel.is_phongvan_online.observeAsState(initial = false)

    Column() {

    Row() {
        Text(
            text = "Phỏng vấn online",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left
        )
        Switch(
            modifier = Modifier.semantics { contentDescription = "" },
            checked = is_phongvan_online_value,
            onCheckedChange = { viewModel.onChangedIsPhongVanOnline(it) }

        )

    }

    TextField(

        label = { Text(text = "Địa diểm phỏng vấn") },

        modifier = Modifier.fillMaxWidth(),
        value = diadiem_henphongvan_value,
        singleLine = true,
        maxLines = 1,
        onValueChange =  { viewModel.onChangedDiaDiemHenPhongVan(it) },

        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.LocalPostOffice,
                contentDescription = null
            )
        }

    )

    TextField(

        label = { Text(text = "Link Phỏng vấn online") },

        modifier = Modifier.fillMaxWidth(),
        value = link_phongvan_online_value,
        singleLine = true,
        maxLines = 1,
        onValueChange =  { viewModel.onChangedLinkPhongVanOnline(it) },

        leadingIcon = {
            Icon (
                imageVector = Icons.Outlined.OnlinePrediction,
                contentDescription = null
            )
        }

    )





    Text (
        text = "Có nhắc nhở",
       // modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Text(

        text = "Thời gian nhắc nhở",
      //  modifier = Modifier.align(Alignment.CenterHorizontally)

    )

    Button(

        onClick = {

        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(text = "Xác nhận")
    }
    }
}