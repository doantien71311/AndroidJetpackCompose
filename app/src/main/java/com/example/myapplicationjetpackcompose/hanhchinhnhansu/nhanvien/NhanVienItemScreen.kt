package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.formatToNgayThangNamVN
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienItemScreen (
    navController: NavController,
    context : Context,
    state: dm_nhanvien_cus,
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
    ) {

        Column () {

            //region tiêu để nhân viên
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.outlineVariant)
                .padding(
                    start = 5.dp,
                    end = 5.dp,
                )
                .fillMaxWidth(),

        ) {

            Text(
                text = state.ten_nv ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                softWrap = true,
                overflow = TextOverflow.Clip,
                fontWeight = FontWeight.Bold,


                )

            Text(
                text = state.ma_nv ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                softWrap = true,
                overflow = TextOverflow.Clip,
                fontWeight = FontWeight.Bold,


                )

        }
            //endregion tiêu để nhân viên


            Row () {

                //region hình ảnh nhân viên
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(state.hinhanh_daidien_url?:"")
                        .crossfade(true)
                        .build(),
                    contentDescription = "ImageRequest example",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .background(Color.Red)

                )
                //endregion hình ảnh nhân viên

                //region thông tin cá nhân
                Column() {

                    Text(
                        text = state.dienthoai_lienhe ?: "",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        fontWeight = FontWeight.Bold,

                        )

                    Text(
                        text = state.email ?: "",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        fontWeight = FontWeight.Bold,

                        )


                    Text(
                        text = state.ngaysinh.formatToNgayThangNamVN(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        fontWeight = FontWeight.Bold,

                        )


                    Text(
                        text = state.ngayvaolam.formatToNgayThangNamVN(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        fontWeight = FontWeight.Bold,

                        )


                }
                //endregion


            }

            //region Chức vụ


            Row () {

            Text(
                text = state.ma_chucvu ?: "",
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

                Text(
                    text = state.ten_chucvu ?: "",
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxWidth()
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

            }

            //endregion Chức vụ

            //region chi nhánh


            Row () {

                Text(
                    text = state.ma_chinhanh ?: "",
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

                Text(
                    text = state.ten_chinhanh ?: "",
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxWidth()
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

            }

            //endregion chi nhánh

        }
    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NhanVienItemScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        val list = dm_nhanvien_cus(
            ma_nv = "NV0001",
            ten_nv = "tên nhân viên 001",
            hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
            ma_chucvu = "AG",
            ten_chucvu = "tên AG",
            ma_chinhanh = "CN0001",
            ten_chinhanh = "Chi nhánh 00001",
            dienthoai_lienhe = "0846356995",
            email = "buivantienthĩ@gmail.com",
            ngaysinh = LocalDateTimeGetNow(),
            ngayvaolam = LocalDateTimeGetNow(),

        )
        NhanVienItemScreen(
            navController = navHostController,
            context = current,
            state =  list,
        )
    }
}