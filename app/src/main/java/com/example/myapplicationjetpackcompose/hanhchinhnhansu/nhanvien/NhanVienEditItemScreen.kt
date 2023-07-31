package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import java.io.File


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienEditItemScreen (
    navController: NavController,
    context : Context,
    state: dm_nhanvien_cus,
    onEvent: (NhanVienEditEvent) -> Unit

) {

    var selectImageUri by remember {
       mutableStateOf<Uri?>(value = null)
    }

    var singlePhotoPickkerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia() ,
        onResult = { uri ->

            selectImageUri = uri
            Log.d("PhotoPicker", "select URI: ${uri.toString()}")

           // val path = Environment.getExternalStoragePublicDirectory(
            //    Environment.DIRECTORY_PICTURES
          ///  )
         //   val file = File(path, "DemoPicture.jpg")
           // file.createNewFile()

            uri?.let {
                val stream = context.contentResolver.openInputStream(it)

                 val path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                 )
                   val file = File(path, "DemoPicture.jpg")
                 file.createNewFile()


//                 file.outputStream().use {
//
//                     context.assets.open("DemoPicture.jpg").copyTo(it)
//                 }

              //  onEvent(NhanVienEditEvent.UploadImageNhanvienDaidien(file))

                onEvent(NhanVienEditEvent.UploadImageNhanvienDaidien(file))


            }

          // val a = URI(uri?.toString())

          //val file = File(a)
            //val file = File(uri?.toString())



//            val file = Uri.fromFile(
//            File(
//                context.cacheDir,
//                context.contentResolver.(uri!!)
//            )
//        ).toFile()




        }
    )


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row (
                 modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
                    )
            {
                Button(onClick = {
                   singlePhotoPickkerLauncher.launch(
                       PickVisualMediaRequest (
                           ActivityResultContracts.PickVisualMedia.ImageOnly
                       )
                   )
                }) {
                    Text(text = "Pick one photo")
                }

            }
        }

        item {
            AsyncImage(
                model = selectImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }


    }

    Scaffold(
        topBar = {
            TopAppBar(


                title = { Text(text = "Nhân viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                        //onEvent(NhanVienEvent.SaveData)

                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),

                )
        },

        content = {
            //region hình ảnh nhân viên
            Column(
                modifier = Modifier
                    .padding(it)
                    .size(100.dp)
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
                        .clickable {

                            singlePhotoPickkerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )

                        }
                )
            }
            //endregion hình ảnh nhân viên
        }
    )



}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NhanVienEditItemScreenPreview() {

    lateinit var navHostController: NavHostController

    MyApplicationJetpackComposeTheme {

        navHostController = rememberNavController()
        val current = LocalContext.current

        val list = dm_nhanvien_cus(
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
        NhanVienEditItemScreen(
            navController = navHostController,
            context = current,
            state =  list,
            onEvent = {}
        )
    }
}