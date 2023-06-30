package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OnlinePrediction
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BoxScreen (
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        ) {


            Column(
                modifier = Modifier
                    .background(Color.Green)
//                    .height(800.dp)
//                    .width(300.dp),
                    .fillMaxHeight()
                    .fillMaxWidth(),


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
                    .align(
                        alignment = Alignment.BottomCenter
                    )

                    .height(100.dp)
                    .width(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom

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

            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun BoxScreenPreview() {
    MyApplicationJetpackComposeTheme {
        BoxScreen()
    }
}