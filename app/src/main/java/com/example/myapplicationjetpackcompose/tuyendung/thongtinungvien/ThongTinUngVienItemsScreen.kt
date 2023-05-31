package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.Destination
import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@Composable()
fun ThongTinUngVienItemsScreen(

    navController: NavController,
    context : Context


) {

    Card (

        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                top = 10.dp,
                start = 10.dp,
                end = 10.dp

            )
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Blue)
            .fillMaxWidth()
            .height(200.dp)
            .clickable {

            },
        //elevation = 8.dp
    ) {

        Column ( ) {

            Text(
                text = "Họ tên",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

            Text(
                text = "Điện thoại",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

            Text(
                text = "Ngày đăng ký",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

            Text(
                text = "Chức vụ ứng tuyển",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

            Text(
                text = "Email",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )



            Row (
                modifier = Modifier
                    //.align(Alignment.CenterHorizontally)
                    .padding(
                        //bottom = 10.dp,
                        top = 10.dp,
                       // start = 10.dp,
                       // end = 10.dp

                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)

                ) {

                Button(
                    onClick = {


                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp)
                ) {
                    Text(text = "Phỏng vấn")
                }

                Button(
                    onClick = {


                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp)
                ) {
                    Text(text = "Kích hoạt")
                }
            }


        }


    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ThongTinUngVienItemsScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        NavigationAppHost(navController = navHostController)

        ThongTinUngVienItemsScreen (navHostController, current)
    }
}