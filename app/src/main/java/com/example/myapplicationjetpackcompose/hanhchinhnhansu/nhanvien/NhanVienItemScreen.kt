package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.formatToNgayThangNamVN
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.providers.provider_dm_nhanvien_cus
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
            .height(350.dp)
            .animateContentSize()
            .clickable {

                navController.navigate(
                    MainMenuDestination.DANHMUC_NhanVien_Edit.passParamater(
                        keyvalue = state.id.toString()
                    )
                )


            },
    ) {

        Column (
            modifier = Modifier.padding(
                //bottom = 5.dp,
                //top = 5.dp,
                start = 5.dp,
                end=5.dp,
                ),
           verticalArrangement = Arrangement.SpaceBetween


        )
        {

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


            Row (
                verticalAlignment = Alignment.CenterVertically
                    ) {

                //region hình ảnh nhân viên
                Column (
                    modifier = Modifier.size(100.dp)
                        .clip(CircleShape)
                        //.border(5.dp, Color.Gray, CircleShape)
                       // .background(color = MaterialTheme.colorScheme.onPrimary)
                        .background(color = Color.White)
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(state.hinhanh_daidien_url ?: "")
                            .crossfade(true)
                            .build(),
                        contentDescription = "ImageRequest example",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.Blue)
                            .clip(CircleShape)
                            //.clip(RoundedCornerShape(10.dp))
                            //.border(5.dp, Color.Gray, CircleShape)//optional
                    )
                }
                //endregion hình ảnh nhân viên

                //region thông tin cá nhân
                Column(
                    modifier = Modifier.padding(
                    bottom = 5.dp,
                    start = 5.dp,
                    end=5.dp,
                    )
                ) {

                    //region điện thoaị liên hệ
                    Row (
                        modifier = Modifier.padding(
                            bottom = 5.dp,
                            start = 5.dp,
                            end=5.dp,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                            )
                    {

                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp),

                            )

                        Text(
                        text = state.dienthoai_lienhe ?: "",
                        textAlign = TextAlign.Start,
                       // style = MaterialTheme.typography.titleLarge,
                        softWrap = true,
                        overflow = TextOverflow.Clip,
                        //fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                start = 5.dp,
                            ),

                        )
                    }
                    //endregion điện thoaị liên hệ

                    //region email
                    Row (

                        modifier = Modifier.padding(
                            bottom = 5.dp,
                            start = 5.dp,
                            end = 5.dp,
                        ),
                        verticalAlignment = Alignment.CenterVertically

                            ) {

                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                        )

                        Text(
                            text = state.email ?: "",
                            textAlign = TextAlign.Start,
                          //  style = MaterialTheme.typography.bodyLarge,
                            softWrap = true,
                            overflow = TextOverflow.Clip,
                            //fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                start = 5.dp,
                            ),

                            )
                    }
                    //endregion email

                    //region ngày vào làm
                    Row (

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
                            modifier = Modifier.size(30.dp),
                        )

                        Text(
                            text = state.ngayvaolam.formatToNgayThangNamVN(),
                            textAlign = TextAlign.Start,
                            //style = MaterialTheme.typography.titleLarge,
                            softWrap = true,
                            overflow = TextOverflow.Clip,
                            //fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                start = 5.dp,
                            ),

                            )
                    }
                    //endregion ngày vào làm


                }
                //endregion


            }

            //region Chức vụ
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            )

            {

            Column (
                modifier = Modifier
                    //.background(color = Color.Green)
                    .padding(
                        end = 5.dp,
                    ),
            )
            {


                Row (

                    verticalAlignment = Alignment.CenterVertically,


                        )
                {

                    Icon(Icons.Default.Star,
                        "",
                         modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "Chức vụ",
                        modifier = Modifier
                            // .fillMaxWidth()
                            .padding(
                                start = 5.dp,
                                end = 5.dp
                            ),
                        textAlign = TextAlign.Start,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,

                        )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(

                        modifier = Modifier.size(50.dp)
                            .clip(CircleShape)
                            .background(color = Color.Green)


                        // .background(color = MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Text(
                            text = state.ma_chucvu ?: "",
                            modifier = Modifier
                                .align(Alignment.Center)
                            ,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,

                            )
                    }

                    Text(
                        text = state.ten_chucvu ?: "",
                        modifier = Modifier

                            //  .fillMaxWidth()
                            .padding(
                                start = 5.dp,
                            ),
                        textAlign = TextAlign.Start,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,


                        )

                }



              }



            }

            //endregion Chức vụ

            //region chi nhánh


            Column (
                modifier = Modifier
                    .padding(
                        //top = 5.dp
                    )
               // .background(color = Color.Green)

                    ) {


                Row (

                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                        ) {

                    Icon(Icons.Default.Home,
                        "",
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "Văn phòng",
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                end = 5.dp
                            ),
                        textAlign = TextAlign.Start,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,

                        )
                    Icon(Icons.Default.Code,
                        "",
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = state.ma_chinhanh ?: "",
                        modifier = Modifier
                            .padding(
                                start = 5.dp
                            )
                            //.width(50.dp)
                            //.height(50.dp)
                            //.clip(RoundedCornerShape(30.dp))
                           ,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,

                        )

                }

                Text (
                    text = state.ten_chinhanh ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                       ,
                    textAlign = TextAlign.Start,
                    fontStyle = FontStyle.Italic,
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
fun NhanVienItemScreenPreview(
   // @PreviewParameter(provider_dm_nhanvien_cus::class , 1) state:dm_nhanvien_cus
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        val list = dm_nhanvien_cus (
            ma_nv = "NV0001",
            ten_nv = "tên nhân viên 001",
            hinhanh_daidien_url = "https://fwm-hinhanh.theworldlink.vn//FWM/WebPortal/NhanVien/DaiDien/hinh_anh_sep_quan_41ddbcae-9ff1-43f8-bd5c-8fde9750f054.png",
            ma_chucvu = "AGG",
            ten_chucvu = "Chuyên viên tư vấn cấp cao lắm luôn đó nha nha",
            ma_chinhanh = "CN0001",
            ten_chinhanh = "Văn phòng 00001 khu vực đông nam bộ",
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