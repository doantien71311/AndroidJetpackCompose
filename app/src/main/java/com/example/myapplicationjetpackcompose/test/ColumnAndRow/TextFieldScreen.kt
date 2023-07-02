package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.SettingsSystemDaydream
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.OnlinePrediction
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TextFieldScreen (
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.error
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.Red),


            ) {


            Column() {

                Row(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(
//                        start = 50.dp,
//                        end = 100.dp,
                          //  bottom = 50.dp
                        )

//                        .align(
//                            alignment = Alignment.CenterHorizontally
//                        )
                        .clickable {


                        }
                    ,
                   horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "AG",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp)
//                            .align(
//                                Alignment.CenterVertically
//                            )
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.Gray)
                            .padding(
                               start = 5.dp,
                                end = 5.dp,
                                bottom = 5.dp,
                                top = 13.dp,
                            )
                        ,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,


                    )

                    Text(
                        modifier = Modifier

                            .padding(
                                start = 10.dp
                        ),
                        //text = "TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH TƯ VẤN TÀI CHÌNH",
                        text = "TƯ VẤN TÀI CHÌNH",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,

                    )

                }


                var ma_nsd = remember { mutableStateOf(TextFieldValue("")) }

                TextField(
                    label = { Text(text = "Mã code tuyển dụng") },
                    modifier = Modifier
                        .fillMaxWidth()
//                .padding(
//                    start = 10.dp,
//                    end = 10.dp
//                )
                    ,
                    singleLine = true,
                    maxLines = 1,
                    value = ma_nsd.value,
                    onValueChange = { ma_nsd.value = it },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null
                        )
                    }

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldScreenPreview() {
    MyApplicationJetpackComposeTheme {
        TextFieldScreen()
    }
}