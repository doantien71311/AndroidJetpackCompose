package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienViewModel
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import org.checkerframework.common.subtyping.qual.Bottom

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ColumnAndRowScreen (
)

{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {


        Column( modifier = Modifier
            //.width(350.dp)
            .fillMaxWidth()
            .background(Color.Yellow),

           // horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            //horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {


            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .height(500.dp)
                     .width(300.dp),

                verticalArrangement = Arrangement.Center

            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(Color.Blue)
                        .height(100.dp)
                        .align(
                            Alignment.End
                        )


                ) {
                    Text(text = "Nút Column")
                }

            }
            Row(
                modifier = Modifier
                    .background(Color.Red)
                    .padding(
//                        start = 50.dp,
//                        end = 100.dp,
                      //  bottom = 50.dp
                    )
                    .height(100.dp)
                    .width(500.dp)

                    .align(
                        alignment = Alignment.End
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top

            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(Color.Blue)
                        .height(70.dp)
                        .align(
                            Alignment.CenterVertically
                        )


                ) {
                    Text(text = "Nút Row")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(Color.Blue)
                        .height(70.dp)
                        .align(
                            Alignment.CenterVertically
                        )


                ) {
                    Text(text = "Nút Row 3")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(Color.Blue)
                        .height(70.dp)
                        .align(
                            Alignment.CenterVertically
                        )


                ) {
                    Text(text = "Nút Row 4")
                }

            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnAndRowScreenPreview() {
    MyApplicationJetpackComposeTheme {
        ColumnAndRowScreen()
    }
}