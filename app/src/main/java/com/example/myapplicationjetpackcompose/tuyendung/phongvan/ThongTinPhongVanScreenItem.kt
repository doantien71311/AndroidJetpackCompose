package com.example.myapplicationjetpackcompose.tuyendung.phongvan

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination

@Composable()
fun ThongTinPhongVanScreenItem (
    navController: NavController,
    context : Context,
    pos: Int
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
            .height(250.dp)
            .animateContentSize()

            .clickable {



            },
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 50.dp,
//            focusedElevation = 100.dp
//        ),
        border = BorderStroke(2.dp, Color.Black)
    ) {

        Column (
            modifier = Modifier
                .background(
                    if ( pos == 1) {
                        Color.Yellow
                    } else {
                        Color.Green
                    })
                .fillMaxSize()

                //.fillMaxWidth()
                //.fillMaxHeight()

        )
        {

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

            Text(
                text = "Thời gian phỏng vấn",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

            Text(
                text = "Địa diểm phỏng vấn",
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Text(
                text = "Có nhắc nhở",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(

                text = "Thời gian nhắc nhở",
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Button(

                onClick = {

                    navController.navigate(MainMenuDestination.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet
                        .passParamater("sadadsadsdadasd"))

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(text = "Kích hoạt")
            }


        }


    }


}