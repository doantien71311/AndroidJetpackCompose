package com.example.myapplicationjetpackcompose.mainmenu

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.launch

//@Composable
//fun CarMenuSrceen(viewModel: MainMenuViewModel) {
//
////    Text(
////        text = viewModel.ListMenuApp[1].ten_chucnang!!,
////       // text = viewModel.RowMenuApp.ten_chucnang!!,
////        //  modifier = Modifier.align(Alignment.CenterHorizontally)
////
////
////    )
//
//    LazyColumn (
//         //  modifier = Modifier.verticalScroll(rememberScrollState())
//    )
//
//    {
//
//
//        itemsIndexed(
//            items =  viewModel.ListMenuApp.toTypedArray()
//
//        ) { index, item ->
//
//            Text(
//                text = item.ten_chucnang!!,
//                //  modifier = Modifier.align(Alignment.CenterHorizontally)
//
//
//            )
//
//             RowsCarMenuSrceen(item.menu_app_chitiet!!.toTypedArray())
//
//        }
//
//    }
//
//
//}

@Composable
fun CarMenuSrceen() {


    val m_MainMenuViewModel : MainMenuViewModel = viewModel()
    m_MainMenuViewModel.loadData()

    LazyColumn (
        //  modifier = Modifier.verticalScroll(rememberScrollState())
    )

    {




            itemsIndexed(
                items = m_MainMenuViewModel.ListMenuApp.toTypedArray()

            ) { index, item ->

                Text(
                    text = item.ten_chucnang!!,
                    //  modifier = Modifier.align(Alignment.CenterHorizontally)


                )

                RowsCarMenuSrceen(item.menu_app_chitiet!!.toTypedArray())

            }
        }




}


@Composable
fun RowsCarMenuSrceen(para: Array<dto_menu_app_chitiet>) {

    LazyRow (
      // modifier = Modifier.horizontalScroll(rememberScrollState()),

    ) {
        itemsIndexed (
           items =  para
        )  { index, item ->

            ItemsCarMenuSrceen(item)

        }
    }

}


@Composable
fun ItemsCarMenuSrceen(para: dto_menu_app_chitiet) {

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
                    .data(para.url_hinhanh)
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
                text = para.ten_chucnang!!,
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

           var lis : List<dto_menu_app>
           lis = ArrayList<dto_menu_app>()



        //CarMenuSrceen(lis.toTypedArray())
    }
}

