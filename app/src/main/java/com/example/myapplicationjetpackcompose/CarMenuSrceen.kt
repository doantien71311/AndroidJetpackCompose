package com.example.myapplicationjetpackcompose

import android.content.res.Configuration
import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@Composable
fun CarMenuSrceen() {


    Column ( modifier = Modifier.verticalScroll(rememberScrollState())  ) {


        Text(
            text = "Menu 1",
            //  modifier = Modifier.align(Alignment.CenterHorizontally)


        )
        RowsCarMenuSrceen()


        Text(
            text = "Menu 2",
            //  modifier = Modifier.align(Alignment.CenterHorizontally)


        )

        RowsCarMenuSrceen()

        RowsCarMenuSrceen()

        RowsCarMenuSrceen()


    }




}

@Composable
fun RowsCarMenuSrceen() {


    Row (
        modifier = Modifier.horizontalScroll(rememberScrollState()),

    ) {


        ItemsCarMenuSrceen()

        ItemsCarMenuSrceen()

        ItemsCarMenuSrceen()

        ItemsCarMenuSrceen()


    }

}



@Composable
fun ItemsCarMenuSrceen() {

    val url_hinh : String =  "https://daiichitheworldlink-hinhanh.theworldlink.vn/TheWorldLink/WebPortal/Images/logo.png"

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
            .width(300.dp)
            .height(300.dp)

        //.clickable {  },
        //elevation = 8.dp
    ) {

        Column ( ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url_hinh)
                    .crossfade(true)
                    .build(),
                contentDescription = "ImageRequest example",
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .height(250.dp)
                    //.width(100.dp)
                    .fillMaxWidth()
                    //.fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Menu 01 456",
                modifier = Modifier.align(Alignment.CenterHorizontally)


            )

        }


    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CarMenuSrceenPreview() {
    MyApplicationJetpackComposeTheme {
          CarMenuSrceen()
    }
}

