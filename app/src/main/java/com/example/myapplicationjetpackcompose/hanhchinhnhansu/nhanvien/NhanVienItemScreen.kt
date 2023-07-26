package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienItemScreen (
    navController: NavController,
    context : Context,
    state: dm_nhanvien_cus,
) {

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 5.dp,
                top = 5.dp,
                start = 5.dp,
                end = 5.dp

            )
            //.clip(RoundedCornerShape(0.dp))
            //.background(color = Color.Blue)
            .fillMaxWidth()
            .height(250.dp)
            .animateContentSize()
            .clickable {


            },
    ) {

        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.outlineVariant)
                .padding(
                    start = 5.dp,
                    end= 5.dp,
                )
                .fillMaxWidth(),

        ) {

            Text(
                text = state.ten_nv ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                softWrap = true,
                overflow = TextOverflow.Clip,
                fontWeight = FontWeight.Bold,


                )

            Text(
                text = state.ma_nv ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                softWrap = true,
                overflow = TextOverflow.Clip,
                fontWeight = FontWeight.Bold,


                )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun NhanVienItemScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        var list = dm_nhanvien_cus(ma_nv = "NV0001", ten_nv = "tên nhân viên 001")
        NhanVienItemScreen(
            navController = navHostController,
            context = current,
            state =  list,
        )
    }
}