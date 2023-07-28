package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.services.DataStoreCustomServices
import com.example.myapplicationjetpackcompose.test.ColumnAndRow.ColumnAndRowScreen
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienItemsScreen
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import io.reactivex.internal.operators.completable.CompletableDoOnEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienScreen(
    navController: NavController,
    context : Context,
    state: List<dm_nhanvien_cus>,
    onEvent: (NhanVienEvent) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(


                title = { Text(text = "Nhân viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                        //onEvent(NhanVienEvent.SaveData)

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },

                colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),

            )
        },

        content = {

            val scrollState = rememberLazyListState()
            Column(
                modifier = Modifier.padding(it)
            )
            {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                       .background(Color.Red)
                        //.fillMaxSize()
                    // .padding(it)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    itemsIndexed(
                        items = state,
                    ) { index, item ->

                        NhanVienItemScreen(
                            navController,
                            context,
                            item

                        )
                    }

                }


            }
        }

    )

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NhanVienScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        var list = mutableListOf<dm_nhanvien_cus>()
        list.add(
            dm_nhanvien_cus(ma_nv = "NV0001", ten_nv = "tên nhân viên 001")
        )
        list.add(
            dm_nhanvien_cus(ma_nv = "NV0002", ten_nv = "tên nhân viên 002")
        )
        list.add(
            dm_nhanvien_cus(ma_nv = "NV0003", ten_nv = "tên nhân viên 003")
        )

        NhanVienScreen(
            navController = navHostController,
            context = current,
            state =  list,
            onEvent = {}
        )
    }
}