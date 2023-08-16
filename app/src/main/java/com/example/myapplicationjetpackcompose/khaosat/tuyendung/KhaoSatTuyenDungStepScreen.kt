package com.example.myapplicationjetpackcompose.khaosat.tuyendung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun KhaoSatTuyenDungStepScreen(
    navController: NavController,
    context : Context,

    )
{

 Column (
     modifier = Modifier
         .background(Color.Blue)
         .fillMaxWidth()
         .padding(0.dp),
     verticalArrangement = Arrangement.SpaceBetween

 ) {
     Row(
         modifier = Modifier
             .fillMaxWidth()
             .padding(
                 start = 10.dp,
                 end = 10.dp
             ),
        horizontalArrangement = Arrangement.SpaceEvenly,
         verticalAlignment = Alignment.CenterVertically

     ) {

         //region Nút 1
         Button (
             onClick = {
             },
             colors = ButtonDefaults.buttonColors(
               containerColor = Color.Green
             ),
             //shape = RoundedCornerShape(50),
             modifier = Modifier
                 .padding(0.dp)
                 .clip(CircleShape)
                 .size(50.dp)



            ,
             content = {
                    Text (
                        text = "1",
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                    )
             }
         )
         //endregion nút 1

         //region Nút 2
         Button (
             onClick = {
             },
             modifier = Modifier
                 .padding(10.dp)

             ,
             content = {
                 Text ("2")
             }
         )
         //endregion nút 2


         //region Nút 3
         Button (
             onClick = {
             },
             modifier = Modifier
                 .padding(10.dp)

             ,
             content = {
                 Text ("3")
             }
         )
         //endregion nút 3

         //region Nút 4
         Button (
             onClick = {
             },
             modifier = Modifier
                 .padding(10.dp)

             ,
             content = {
                 Text ("4")
             }
         )
         //endregion nút 4

     }

     Text(
         text = "Tên các bước",
         textAlign = TextAlign.Center,
         modifier = Modifier
             .fillMaxWidth()
             .background(Color.Red)
            // .align(Alignment.Center)
             .padding(0.dp)

     )

 }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KhaoSatTuyenDungStepScreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        KhaoSatTuyenDungStepScreen(
            navController = navHostController,
            context = current,

            )
    }
}


