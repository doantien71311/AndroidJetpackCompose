package com.example.myapplicationjetpackcompose.khaosat.tuyendung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienItemScreen
import com.example.myapplicationjetpackcompose.model.dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun KhaoSatTuyenDungB2ChucVuItemScreen(

    navController: NavController,
    context : Context,
    state: dm_chucvu_ds,

    )
{

    Row(
        modifier = Modifier
            .padding(
               top = 5.dp,
                bottom = 5.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(

            modifier = Modifier.size(50.dp)
                .clip(CircleShape)
                .background(color = Color.DarkGray)


            // .background(color = MaterialTheme.colorScheme.outlineVariant)
        ) {
            Text(
                text = state.ma_chucvu ?: "",
                modifier = Modifier
                    .align(Alignment.Center)
                ,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = Color.Black,

                )
        }

        Text(
            text = state.ten_chucvu ?: "",
            modifier = Modifier

                //  .fillMaxWidth()
                .padding(
                    start = 5.dp,
                ),
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,


            )

    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KhaoSatTuyenDungB2ChucVuItemScreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        val list = dm_chucvu_ds (
            ma_chucvu = "FCC",
            ten_chucvu = "Tư vấn tài chính vfjjgjhjh",

            )
        KhaoSatTuyenDungB2ChucVuItemScreen(
            navController = navHostController,
            context = current,
            state =  list,
        )
    }
}