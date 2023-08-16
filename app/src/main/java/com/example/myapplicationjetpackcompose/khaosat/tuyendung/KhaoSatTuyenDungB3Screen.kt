package com.example.myapplicationjetpackcompose.khaosat.tuyendung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.widget.Space
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun KhaoSatTuyenDungB3Screen(
    navController: NavController,
    context : Context,
    horizontalPagerState: PagerState,
    scope: CoroutineScope
)
{

    val ma_nsd = remember {

        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)
    )
    {

        Column(
            modifier = Modifier
               .background(Color.Green)
                .fillMaxWidth()
                .fillMaxHeight()
                ,
           // verticalArrangement = Arrangement.SpaceBetween
            horizontalAlignment = Alignment.CenterHorizontally



        ) {

            Spacer(modifier = Modifier.height(10.dp))

            //region hiện thị hình ảnh
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://daiichitheworldlink-hinhanh.theworldlink.vn/TheWorldLink/WebCty/Images/khaosatmaster/tuyendung/01_hoptac_kinhdoanh.png")
                    .crossfade(true)
                    .build(),
                contentDescription = "ImageRequest example",
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp)
                    .background(Color.Blue)
                    .padding(0.dp)
            )

            //endregion

            Spacer(modifier = Modifier.height(10.dp))

            //region mã code nhân viên
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {

                TextField(
                    label = { Text(text = "Mã code nhân viên") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),
                    singleLine = true,
                    maxLines = 1,
                    value = ma_nsd.value,
                    onValueChange = { ma_nsd.value = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    }

                )


            }
            //endregion
            Spacer(modifier = Modifier.height(10.dp))

            //region là cộng tác viên

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,


                ) {
                Text(
                    text = "Bạn có phải là cộng tác viên?",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),


                    )


                Switch(
                    modifier = Modifier
                        .semantics { contentDescription = "Demo" }
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),
                    checked = true,
                    onCheckedChange = {

                    })
            }
            //endregion

            Spacer(modifier = Modifier.height(10.dp))

            //region Tên công tác viên
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {

                TextField(
                    label = { Text(text = "Tên công tác viên") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),
                    singleLine = true,
                    maxLines = 1,
                    value = ma_nsd.value,
                    onValueChange = { ma_nsd.value = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null
                        )
                    }

                )


            }
            //endregion Tên công tác viên

            Spacer(modifier = Modifier.height(10.dp))

            //region Diện thoại công tác viên
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {

                TextField(
                    label = { Text(text = "Điện thoại công tác viên") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),
                    singleLine = true,
                    maxLines = 1,
                    value = ma_nsd.value,
                    onValueChange = { ma_nsd.value = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null
                        )
                    }

                )


            }
            //endregion Tên công tác viên
            Spacer(modifier = Modifier.height(10.dp))

            //region nút tiếp theo
            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {


                },
                content = {

                    Text("Tiếp theo 3")


                }
            )
            //endregion nút tiếp theo
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KhaoSatTuyenDungB3ScreenPreview(
) {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current
        val horizontalPagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        KhaoSatTuyenDungB3Screen(
            navController = navHostController,
            context = current,
            horizontalPagerState,
            scope

        )
    }
}