package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row



import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material.icons.filled.PersonRemoveAlt1
import androidx.compose.material.icons.filled.PunchClock
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationjetpackcompose.EnumCoKhong
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.formatToNgayThangNamVN
import com.example.myapplicationjetpackcompose.formatToFullTimeVN
import com.example.myapplicationjetpackcompose.formatToHourMinuteVN
import com.example.myapplicationjetpackcompose.formatToThuNgayThangNamVN
import com.example.myapplicationjetpackcompose.formatToTimeDayVN
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable()
fun ThongTinPhongVanItemScreenTest(
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
                .height(500.dp)
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


                //region tile tên thành viên
                Row (
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.outlineVariant)
                        .padding(
                            start = 5.dp,
                            end = 5.dp,
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

                    Row (

                        modifier = Modifier

                            .padding(
                               0.dp
                            ),
                        horizontalArrangement = Arrangement.End,

                            ) {

                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                //iewModel.chonPhongVan(para_dm_ungvien_cus)
                            }) {
                            Icon(
                                Icons.Default.Timer,
                                "Hẹn phỏng vấn",
                                // modifier = Modifier.size(30.dp)
                            )
                        }

                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                //iewModel.chonPhongVan(para_dm_ungvien_cus)
                            }) {
                            Icon(
                                Icons.Default.PersonRemoveAlt1,
                                "Hủy phỏng vấn",
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
                            Icon(
                                Icons.Default.CheckCircle,
                                "Kích hoạt",
                                //  modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
                //endregion tile tên thành viên



                //region Thời gian hẹn phỏng vấn
                Row(
                    modifier = Modifier
                        .padding(
                            top = 5.dp
                        )
                        .fillMaxWidth()
                        .clickable {


                        }
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Row(
                        modifier = Modifier.padding(
                            bottom = 5.dp,
                            start = 5.dp,
                            end=5.dp,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PunchClock,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),


                            )
                        Column(
                            modifier = Modifier.padding(
                                start = 5.dp
                            )
                        ) {
                            Text(
                                text = "Thời gian hẹn",
                                fontStyle = FontStyle.Italic
                            )

                            Text(
                                text = "${para_dm_ungvien_cus.ngay_henphongvan.formatToHourMinuteVN() ?: ""}",
                                fontWeight = FontWeight.Bold
                            )

                        }


                    }


                    Row(
                        modifier = Modifier.padding(
                            bottom = 5.dp,
                            start = 5.dp,
                            end = 5.dp,
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
                                text = "Ngày hẹn phỏng vấn",
                                fontStyle = FontStyle.Italic
                            )

                            Text(
                                text = "${para_dm_ungvien_cus.ngay_henphongvan.formatToThuNgayThangNamVN() ?: ""}",
                                fontWeight = FontWeight.Bold
                            )

                        }



                    }
                }
                //endregion Thời gian hẹn phỏng vấn


                if ((para_dm_ungvien_cus.is_phongvan_online == true)){

                    //region link phỏng vấn online

                    Row(
                        modifier = Modifier
                            .padding(
                                bottom = 5.dp,
                                start = 5.dp,
                                end = 5.dp,
                            )
                            .fillMaxWidth()
                        ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            modifier = Modifier,
                            onClick = {

                            }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                "Coppy",
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Column(
                            modifier = Modifier.padding(
                                start = 5.dp
                            )
                        ) {
                            Text(
                                text = "Link online",
                                fontStyle = FontStyle.Italic
                            )

                            Text(
                                text = "${para_dm_ungvien_cus.link_phongvan_online?: ""}",
                                fontWeight = FontWeight.Bold
                            )

                        }


                    }


                    //endregion link phỏng vấn online

                }

                else {

                    //region Địa diểm hẹn phỏng vấn

                    Row(
                        modifier = Modifier
                            .padding(
                                bottom = 5.dp,
                                start = 5.dp,
                                end = 5.dp,
                            )
                            .fillMaxWidth()
                        ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            modifier = Modifier,
                            onClick = {

                            }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                "Coppy",
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Column(
                            modifier = Modifier.padding(
                                start = 5.dp
                            )
                        ) {
                            Text(
                                text = "Địa điểm",
                                fontStyle = FontStyle.Italic
                            )

                            Text(
                                text = "${para_dm_ungvien_cus.diadiem_henphongvan?: ""}",
                                fontWeight = FontWeight.Bold
                            )

                        }

                    }


                    //endregion Địa diểm hẹn phỏng vấn
                }


                //region Nhắc nhở
                Row(
                    modifier = Modifier
                        .padding(
                            bottom = 5.dp,
                            start = 5.dp,
                            end = 5.dp,
                        )
                        .fillMaxWidth()
                        .clickable {


                        }
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(

                        imageVector = Icons.Default.Today,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),

                        )

                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween

                            )
                    {

                        Column(
                            modifier = Modifier.padding(
                                start = 5.dp,
                            )
                        ) {
                            Text(
                                text = "Nhắc nhở",
                                fontStyle = FontStyle.Italic,
                            )

                            Checkbox (
                                modifier = Modifier.padding(
                                    top = 0.dp,
                                ),
                                enabled = false,
                                checked = para_dm_ungvien_cus.is_nhacnho==EnumCoKhong.C ,
                                onCheckedChange = {

                                }
                            )



                        }

                        Column(
                            modifier = Modifier.padding(
                                start = 5.dp
                            )
                        ) {

                            Text(
                                text = "Số phút nhắc nhở",
                                fontStyle = FontStyle.Italic,
                            )

                            Text(
                                text = "${para_dm_ungvien_cus.sophut_nhacnho?: ""}",
                                fontWeight = FontWeight.Bold

                            )

                        }
                    }




                }
                //#endregion Nhắc nhở

                Divider()

                //region thông tin tuyền dụng
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


                //endregion thông tin tuyền dụng

            }


        }

    }
}

@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ThongTinPhongVanItemScreenTestPreview() {


    MyApplicationJetpackComposeTheme {

        ThongTinPhongVanItemScreenTest (
            99,
            dm_ungvien_cus(
                ten_uv = "Nguyễn văn thị aaaaa",
                vitri_ungtuyen = "FC",
                ten_vitri_ungtuyen = "asdsadasdasd asdasdas",
                is_phongvan_online = true,
                diadiem_henphongvan = "Số 47, đ0ong sada saduasdgad, pung82sd sadadas, quan dalsdsad, hochi1 minh",
                link_phongvan_online = "https://vnexpress.net/tai-sao-my-thua-kem-trung-quoc-ve-tau-cao-toc-4625670.html",
                ngay_dangky =  LocalDateTimeGetNow(),
                email = "mrtienasdsad@gmail.com",
                so_dienthoai = "12451545651564",
                ngay_henphongvan =  LocalDateTimeGetNow(),
                sophut_nhacnho = 15,
                is_nhacnho = "C"

            )
        )
    }
}