package com.example.myapplicationjetpackcompose.dangnhap


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DangNhapPage(
    navController: NavController,
    viewModel: DangNhapViewModel = hiltViewModel()
) {


    val ma_nsd : String by viewModel.ma_nsd.observeAsState(initial = "")
    val mat_khau : String by viewModel.mat_khau.observeAsState(initial = "")


    if (viewModel.login_enable) {
        val ctx = LocalContext.current
        ctx.startActivity(Intent(ctx, MainMenuActivity::class.java))
    }

Card(

    shape = RoundedCornerShape(0.dp)

){


    Column(
        modifier = Modifier.padding(0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(

            shape = RoundedCornerShape(0.dp)

        )
        {
            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(189.dp)
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewModel.row_ht_thongtindoanhnghiep!!.hinhanh_logo)
                        .crossfade(true)
                        .build(),
                    contentDescription = "ImageRequest example",
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                )
            }
        }


        Card(

            shape = RoundedCornerShape(80.dp, 80.dp, 0.dp, 0.dp ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),

        )
        {
            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Card(

                    modifier = Modifier
                        .padding(20.dp)
                )
                {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(color = Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            label = { Text(text = "Mã code") },
                            // placeholder = { Text(text = "Mã code") },
                            modifier = Modifier.fillMaxWidth(),
                            value = ma_nsd,
                            singleLine = true,
                            maxLines = 1,
                            onValueChange =  { viewModel.onMaNsdChanged(it) },

                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            }

                        )

                        Spacer(modifier = Modifier.height(20.dp))





                        var showPassword = rememberSaveable { mutableStateOf(false) }
                        val focusManager = LocalFocusManager.current

                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text (text = "Mật khẩu")
                                    },
                            value = mat_khau,
                             onValueChange = {
                                 viewModel.onMatKhauChanged(it)
                                             },

                            //placeholder = { Text(text = "Mật khẩu") },

                            singleLine = true,
                            maxLines = 1,
                            enabled = true,
                            readOnly = false,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color(0xFF636262),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,

                            ),

                            visualTransformation =
                            if (!showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,

                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),

                           leadingIcon = {
                               Icon(
                                   imageVector = Icons.Outlined.Lock,
                                   contentDescription = null
                               )
                           },
                            trailingIcon = {
                                if (showPassword.value) {
                                IconButton(onClick = {
                                    showPassword.value = false
                                }) {
                                    Icon(
                                       imageVector = Icons.Filled.Visibility,
                                        contentDescription = null
                                    )
                                }
                                }
                                else {
                                    IconButton(onClick = {   showPassword.value = true }) {
                                        Icon(
                                            imageVector = Icons.Filled.VisibilityOff,
                                            contentDescription = null
                                        )
                                    }

                                }
                            },
                        )



                        Spacer(modifier = Modifier.height(20.dp))

                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

                            Button(onClick = {

                                viewModel.KiemTra_NSD()

                            },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(text = "Đăng nhập")
                            }
                        }


                    }

                }




            }
        }



    }

}


}


@Composable
fun Example() {
    NormalTextField(label = "Email") {
        Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTextField(
    label: String,
    Icon: @Composable (() -> Unit)
) {
    val (text, setText) = mutableStateOf("")
    TextField(
        leadingIcon = Icon,
        value = text,
        onValueChange = setText,
        label = { Text(text = label) }
    )
}