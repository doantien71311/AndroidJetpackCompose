package com.example.myapplicationjetpackcompose.mainmenu

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.MainActivity
import com.example.myapplicationjetpackcompose.R
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.services.EnumFirebaseMessagingService



@Composable
fun MainMenuItemsScreen(
    navController: NavController,
    context : Context,
    para: dto_menu_app_chitiet,
) {

    Card (

        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 20.dp,
                top = 20.dp,
                start = 10.dp,
                end = 10.dp

            )
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color.Blue)
            .width(200.dp)
            .height(200.dp)

            .clickable {

                // Fetching the local context for using the Toast
                //  Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
                //val chucnang = com.example.myapplicationjetpackcompose.mainmenu.Destination.DANHMUC_NhanVien.route

                navController.navigate( para.ma_chucnang ?: "")


            },
        //elevation = 8.dp
    ) {

        Column (


                ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data( para.icon_img?:"")
                    .crossfade(true)
                    .build(),
                contentDescription = "ImageRequest example",
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .height(150.dp)
                    .width(150.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)

            )

            Text (
                text = para.ten_chucnang?:"",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

        }


    }

}