package com.example.myapplicationjetpackcompose.mainmenu.homepage

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@Composable
fun MainMenuTabsHomePageItemSreen(
    navController: NavController,
    context : Context,
    para: dto_menu_app_chitiet,
) {

    Card (
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .width(200.dp)
            .height(200.dp)
            .background(color = Color.Green)
            .clickable {

                // Fetching the local context for using the Toast
                //  Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()

                navController.navigate( para.ma_chucnang ?: "")




            },
        //elevation = 8.dp
    ) {

        Column (
            modifier = Modifier
                .padding(
                    0.dp
                ).fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Red)
                .align(Alignment.CenterHorizontally)

        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data( para.icon_img?:"")
                    .crossfade(true)
                    .build(),
                contentDescription = "ImageRequest example",
                modifier = Modifier
                    .padding(top= 15.dp , bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .width(150.dp)
                    .height(110.dp)
                    .padding(0.dp)
                    .align(Alignment.CenterHorizontally)

            )

            Text(
                text = para.ten_chucnang?:"",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(color = Color.Red),
                textAlign = TextAlign.Center,

                )

        }


    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuTabsHomePageItemSreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current




        var list = dto_menu_app_chitiet(
            "DANHMUC_TaiLieu_BanHang",
            "Tài liệu bán hàng",
            ""
        )



        MainMenuTabsHomePageItemSreen(
            navController = navHostController,
            context = current,
            para =  list,
        )
    }
}