package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienViewModel
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.getThongTinUngVienViewModel



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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChecklistRtl
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material.icons.rounded.PersonAddAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.formatToFullTimeVN
import com.example.myapplicationjetpackcompose.formatToParamater
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalAnimationApi::class)
@Composable()
fun ThongTinUngVienItemsTestScreen(
    pos: Int,
    para_dm_ungvien_cus: dm_ungvien_cus
)
{

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
                    bottom = 5.dp,
                    top = 5.dp,
                    start = 5.dp,
                    end = 5.dp

                )
                //.clip(RoundedCornerShape(0.dp))
                //.background(color = Color.Blue)
                .fillMaxWidth()
                .height(300.dp)
                .animateContentSize()


                .clickable {


                },
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 50.dp,
//            focusedElevation = 100.dp
//        ),
          //  border = BorderStroke(2.dp, Color.Black),
        ) {

            Column(
                modifier = Modifier
                    .background(
                        if (pos == 1) {
                            Color.Yellow
                        } else {
                            //MaterialTheme.colorScheme.background
                            MaterialTheme.colorScheme.surfaceVariant

                        }
                    )
                    .fillMaxWidth()
                    .fillMaxHeight()
                ,
                verticalArrangement = Arrangement.SpaceBetween
                   // .height(250.dp)
//                    .padding(
//                        start = 5.dp,
//                        end= 5.dp,
//                    )

            )
            {

                Row (
                    modifier = Modifier
                       .background(color = MaterialTheme.colorScheme.outlineVariant)
                        .padding(
                            start = 5.dp,
                            end= 5.dp,
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                    ) {

                    Text(
                        text = para_dm_ungvien_cus.ten_uv ?: "",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        fontWeight = FontWeight.Bold,


                    )

                    Row () {
                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                //iewModel.chonPhongVan(para_dm_ungvien_cus)
                            }) {
                            Icon(Icons.Default.PersonAddAlt,
                                "Hẹn phỏng vấn",
                               // modifier = Modifier.size(30.dp)
                            )
                        }

                        IconButton(
                            modifier = Modifier,
                            onClick = {
//                                navController.navigate(
//
//                                    MainMenuDestination.NHAPLIEU_NhanSu_KichHoatThanhVien_Duyet.passParamater(
//                                        keyvalue = para_dm_ungvien_cus.id.toString(),
//                                        tungay = "asddasdasda",
//                                        denngay = "asdsadasdasdasd",
//                                    ),
//
//
//                                    )
                            }) {
                            Icon(Icons.Default.CheckCircle,
                                "Kích hoạt",
                                  //  modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.padding(
                        bottom = 5.dp,
                        top = 5.dp,
                        start = 5.dp,
                        end=5.dp,
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Icon(
//                        imageVector = Icons.Default.AddIcCall,
//                        contentDescription = null
//                    )


                    Text(
                        text = para_dm_ungvien_cus.vitri_ungtuyen ?: "",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = Color.Green)
                            .padding(
                                start = 5.dp,
                                end = 5.dp,
                                bottom = 5.dp,
                                top = 13.dp,
                            ),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,

                        )

                    Column (
                        modifier = Modifier.padding(
                            start = 5.dp
                        )
                    ) {
                        Text(
                            text = "Vị trí ứng tuyển",
                            fontStyle = FontStyle.Italic,

                            )

                        Text(
                            text = "${para_dm_ungvien_cus.ten_vitri_ungtuyen ?: ""}",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,


                            )

                    }
                }


                Row(
                    modifier = Modifier.padding(
                        bottom = 5.dp,
                        start = 5.dp,
                        end=5.dp,
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),


                    )
                    Column(
                        modifier = Modifier.padding(
                            start = 5.dp
                        )
                    ) {
                        Text(
                            text = "Điện thoại",
                        )

                        Text(
                            text = "${para_dm_ungvien_cus.so_dienthoai ?: ""}",
                        )

                    }


                }



                Row (
                    modifier = Modifier.padding(
                        bottom = 5.dp,
                        start = 5.dp,
                        end=5.dp,
                    ),
                    verticalAlignment = Alignment.CenterVertically
                        ) {
                    Icon(
                        imageVector = Icons.Default.Today,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )

                    Column(
                        modifier = Modifier.padding(
                            start = 5.dp
                        )
                    ) {
                    Text(
                        text = "Ngày đăng ký"

                        )

                    Text(
                        text = "${para_dm_ungvien_cus.ngay_dangky?.formatToFullTimeVN()?:""}",

                        )
                    }

                }




                Row(
                    modifier = Modifier.padding(
                        bottom = 10.dp,
                        start = 5.dp,
                        end = 5.dp,
                    ),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                    )
                    Column (
                        modifier = Modifier.padding(
                            start = 5.dp
                        )
                            ) {
                    Text(
                        text = "Email",

                    )
                        Text(
                            text = "${para_dm_ungvien_cus.email ?: ""}",

                            )
                    }
                }




            }


        }

    }
}

@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ThongTinUngVienItemsScreenTestPreview() {


    MyApplicationJetpackComposeTheme {

        ThongTinUngVienItemsTestScreen (
            99,
            dm_ungvien_cus(
                ten_uv = "Nguyễn văn thị aaaaa dfgdgdf",
                vitri_ungtuyen = "FC",
                ten_vitri_ungtuyen = "asdsadasdasd asdasdas",
                //ngay_dangky =  kotlinx.datetime.LocalDateTime(2023,12,31,59,59,59),
                email = "mrtienasdsad@gmail.com",
                so_dienthoai = "12451545651564"

            )
        )
    }
}
