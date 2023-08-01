package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.myapplicationjetpackcompose.CreateImageResquestCustom
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.launch
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

   //region Khoá code
//    var selectImageUri by remember {
//       mutableStateOf<Uri?>(value = null)
//    }
//
//    var singlePhotoPickkerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia() ,
//        onResult = { uri ->
//
////            selectImageUri = uri
////            Log.d("PhotoPicker", "select URI: ${uri.toString()}")
////
////            val path = Environment.getExternalStoragePublicDirectory(
////                Environment.DIRECTORY_PICTURES
////            )
//            //   val file = File(path, "DemoPicture.jpg")
//            // file.createNewFile()
//
//            uri?.let {
////                val inputStream = context.contentResolver.openInputStream(it)
////
////                val path = Environment.getExternalStoragePublicDirectory(
////                    Environment.DIRECTORY_PICTURES
////                )
////                val file = File(path, "daidien.jpg")
////                file.createNewFile()
////
////                inputStream.use { input ->
////                    file.outputStream().use { output ->
////                        input?.copyTo(output)
////                    }
////                }
//
//                val file = CreateImageResquestCustom(context, it, "daidien.jpg")
//
//                onEvent(NhanVienEditEvent.UploadImageNhanvienDaidien(file))
//
//            }
//
//        }
//    )

    //endregion khoá code

    NhanVienEditShowHinhAnhDaiDienScreen(
        context = context,
        onEvent = onEvent
    )


    Scaffold(
        topBar = {

            TopAppBar(

                title = { Text(text = "Nhân viên") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()



                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),

                actions = {

                    IconButton(
                        onClick = {

                            onEvent(NhanVienEditEvent.SaveData)
                        }
                    ) {

                        Icon(Icons.Default.Check, contentDescription = null)

                    }

                }

                )
        },

        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier
                )
                {

                    NhanVienEditHinhAnhDaiDienScreen(
                        context = context,
                        state = state,
                        onEvent = onEvent
                    )

                    //Text(text = state.hinhanh_daidien_url ?: "")

                    NhanVienEditThongTinCaNhanScreen(
                        state = state,
                        onEvent = onEvent
                    )
                }

            }
        }
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienEditShowHinhAnhDaiDienScreen(
    context : Context,
    onEvent: (NhanVienEditEvent) -> Unit
) {

    var selectImageUri by remember {
       mutableStateOf<Uri?>(value = null)
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            AsyncImage(
                model = selectImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienEditHinhAnhDaiDienScreen(
    context: Context,
    state: dm_nhanvien_cus,
    onEvent: (NhanVienEditEvent) -> Unit
) {

    var singlePhotoPickkerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia() ,
        onResult = { uri ->
            uri?.let {

                val file = CreateImageResquestCustom(context, it, "daidien.jpg")

                onEvent(NhanVienEditEvent.UploadImageNhanvienDaidien(file))

            }

        }
    )

    Column(
        modifier = Modifier
    )
    {
        //region hình ảnh nhân viên
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .background(color = Color.White)
                ,
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

        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider()
        Spacer(modifier = Modifier.height(10.dp))
        //endregion hình ảnh nhân viên
    }


}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NhanVienEditThongTinCaNhanScreen(
    state: dm_nhanvien_cus,
    onEvent: (NhanVienEditEvent) -> Unit
) {

    Column() {

        //region Mã code nhân viên
        TextField(
            label = { Text(text = "Mã code") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            singleLine = true,
            maxLines = 1,
            value = state.ma_nv ?: "",
            onValueChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = null
                )
            }

        )
        Spacer(modifier = Modifier.height(10.dp))
        //endregion tên code nhân viên

        //region Tên code nhân viên
        TextField(
            label = { Text(text = "Tên nhân viên") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                ),
            singleLine = true,
            maxLines = 1,
            value = state.ten_nv ?: "",
            onValueChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccessibilityNew,
                    contentDescription = null
                )
            }

        )
        Spacer(modifier = Modifier.height(10.dp))
        //endregion tên code nhân viên

        //region điẹn thoại liên hệ
        TextField(
            label = { Text(text = "Điện thoại liên hệ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            singleLine = true,
            maxLines = 1,
            value = state.dienthoai_lienhe ?: "",
            onValueChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        //endregion điện thoại liên hệ

        //region email
        TextField(
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            singleLine = true,
            maxLines = 1,
            value = state.email ?: "",
            onValueChange = {

            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            //isError = viewModel.state.is_email_error
        )
        //endregion email


    }


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