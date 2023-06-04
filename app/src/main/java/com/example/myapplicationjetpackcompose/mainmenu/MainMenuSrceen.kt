package com.example.myapplicationjetpackcompose.mainmenu

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.services.EnumFirebaseMessagingService
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.delay


@Composable
fun CarMenuSrceen (

    navController: NavController,
    context: Context,
    intent: Intent

) {


        if (intent.hasExtra(EnumFirebaseMessagingService.ma_chucnang)) {

            val m_ma_chucnang : String = intent.getStringExtra(EnumFirebaseMessagingService.ma_chucnang)!!
            val m_key : String = intent.getStringExtra(EnumFirebaseMessagingService.key)!!
            val m_tungay : String = intent.getStringExtra(EnumFirebaseMessagingService.tungay)!!
            val m_denngay : String = intent.getStringExtra(EnumFirebaseMessagingService.denngay)!!
            intent.removeExtra(EnumFirebaseMessagingService.ma_chucnang)

           // navController.navigate(m_ma_chucnang)
        }





    val mainMenuViewModel : MainMenuViewModel = hiltViewModel()
    mainMenuViewModel.loadData()

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true ) {
        delay(5000)
        isLoading = false
    }

//    if (!mainMenuViewModel.isLoadding) {
//        isLoading = false
//    }

    MainMenuLoadingAnimation(

        //isLoadding = mainMenuViewModel.isLoadding,
        isLoadding = isLoading,
        contentAfterLoading = {

            LazyColumn (
                //  modifier = Modifier.verticalScroll(rememberScrollState())
            )

            {

                itemsIndexed(
                    items = mainMenuViewModel.ListMenuApp.toTypedArray()

                ) { index, item ->

                    Text(
                        text = item.ten_chucnang!!,
                        //  modifier = Modifier.align(Alignment.CenterHorizontally)


                    )

                    RowsCarMenuSrceen(
                        navController,
                        context,
                        item.menu_app_chitiet!!.toTypedArray()
                    )

                }
            }


        }
    )


//    LazyColumn (
//        //  modifier = Modifier.verticalScroll(rememberScrollState())
//    )
//
//    {
//
//            itemsIndexed(
//                items = mainMenuViewModel.ListMenuApp.toTypedArray()
//
//            ) { index, item ->
//
//                Text(
//                    text = item.ten_chucnang!!,
//                    //  modifier = Modifier.align(Alignment.CenterHorizontally)
//
//
//                )
//
//                RowsCarMenuSrceen(
//                    navController,
//                    context,
//                    item.menu_app_chitiet!!.toTypedArray()
//                )
//
//            }
//        }




}


@Composable
fun RowsCarMenuSrceen(
    navController: NavController,
    context : Context,
    para: Array<dto_menu_app_chitiet>,
) {

    LazyRow (
      // modifier = Modifier.horizontalScroll(rememberScrollState()),

    ) {
        itemsIndexed (
           items =  para
        )  { index, item ->

            MainMenuItemsScreen(navController,
                context,
                item,
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

