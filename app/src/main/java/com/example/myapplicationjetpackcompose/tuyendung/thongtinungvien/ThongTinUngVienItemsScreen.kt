package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.formatToFullTimeVN
import com.example.myapplicationjetpackcompose.formatToParamater
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalAnimationApi::class)
@Composable()
fun ThongTinUngVienItemsScreen(

    navController: NavController,
    context : Context,
    pos: Int,
    viewModel: ThongTinUngVienViewModel,
    para_dm_ungvien_cus: dm_ungvien_cus
) {

    AnimatedVisibility(
        visible = para_dm_ungvien_cus.isAnimatedVisibility.value,
        exit =
        scaleOut(
            animationSpec = tween(durationMillis = 1000)
        )
//                +
//                shrinkVertically(
//                    animationSpec = tween(durationMillis = 1000)
//                )
                + fadeOut(
            animationSpec = tween(durationMillis = 1000)
        )
                + shrinkOut(
                animationSpec = tween(durationMillis = 2000),
                        shrinkTowards = Alignment.BottomCenter
        )

    ) {

        Card(

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
//            .background(
//                brush = Brush.horizontalGradient(
//                    listOf(
//                        Color(0xFFF518A0),
//                        Color(0xFFB232BD)
//                    )
//                )
//            )
                .fillMaxWidth()
                .height(200.dp)
                .animateContentSize()

                .clickable {


                },
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 50.dp,
//            focusedElevation = 100.dp
//        ),
            border = BorderStroke(2.dp, Color.Black),
        ) {

            Column(
                modifier = Modifier
                    .background(
                        if (pos == 1) {
                            Color.Yellow
                        } else {
                            Color.Green
                        }
                    )
                    .height(200.dp)

            )
            {

                Text(
                    text = para_dm_ungvien_cus.ten_uv ?: "",
                    modifier = Modifier.align(Alignment.CenterHorizontally)


                )

                Text(
                    text = "Điện thoại",
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

                Text(
                    text =  "Ngày đăng ký: ${para_dm_ungvien_cus.ngay_dangky?.formatToFullTimeVN()}",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = para_dm_ungvien_cus.vitri_ungtuyen ?: "",
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

                Text(
                    text = para_dm_ungvien_cus.email ?: "",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )



                Row(
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



                                    viewModel.chonPhongVan(para_dm_ungvien_cus)



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

                            val ab = LocalDate.now()

                            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                            //val tungay = para_dm_ungvien_cus.ngay_dangky.format(formatter)

                            navController.navigate(

                                MainMenuDestination.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet.passParamater(
                                    keyvalue = para_dm_ungvien_cus.id.toString(),
                                    tungay = "asddasdasda",
                                    denngay = "asdsadasdasdasd",
                                ),


                                )


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
}

@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ThongTinUngVienItemsScreenPreview() {

//    lateinit var navHostController: NavHostController
//
//    MyApplicationJetpackComposeTheme {
//
//        navHostController = rememberNavController()
//        val current = LocalContext.current
//
//        NavigationAppHost(navController = navHostController)
//
//        ThongTinUngVienItemsScreen (navHostController, current, 99, dm_ungvien_cus())
//    }
}
