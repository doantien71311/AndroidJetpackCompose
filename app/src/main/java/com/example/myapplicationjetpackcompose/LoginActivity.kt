package com.example.myapplicationjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

   // val m_LoginViewModel by viewModels<LoginViewModel>()

    val m_LoginViewModel: LoginViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LoginPage(m_LoginViewModel)


                    m_LoginViewModel.LoadData()


                    //CoilImageS()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(viewModel: LoginViewModel) {


    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(viewModel.row_ht_thongtindoanhnghiep.hinhanh_logo)
                .crossfade(true)
                .build(),
            contentDescription = "ImageRequest example",
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        val loginEnable: Boolean by viewModel.login_enable.observeAsState(initial = false)

        Text(text = loginEnable.toString() )

        Spacer(modifier = Modifier.height(20.dp))

        val ma_nsd : String by viewModel.ma_nsd.observeAsState(initial = "")
        val mat_khau : String by viewModel.mat_khau.observeAsState(initial = "")

        TextField(
            label = { Text(text = "Mã code") },
           // placeholder = { Text(text = "Mã code") },
            modifier = Modifier.fillMaxWidth(),
            value = ma_nsd,
            singleLine = true,
            maxLines = 1,
            onValueChange =  { viewModel.onMaNsdChanged(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))


        TextField(
            label = { Text(text = "Mật khẩu") },
            value = mat_khau,
            onValueChange = { viewModel.onMatKhauChanged(it) },
            //placeholder = { Text(text = "Mật khẩu") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF636262),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

            Button(onClick = {

                GlobalScope.launch {
                    viewModel.KiemTra_NSD()
                }


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


@Preview(showBackground = true)
@Composable
fun GreetingPreviewLogin() {
    MyApplicationJetpackComposeTheme {

       // val m_model : ht_thongtinhdoanhnghiep = ht_thongtinhdoanhnghiep("","")
       // val m_model : ht_thongtinhdoanhnghiep = ht_thongtinhdoanhnghiep("","")

       // LoginPage(m_model)
    }
}