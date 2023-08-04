package com.example.myapplicationjetpackcompose.mainmenu

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSrceen ()
{
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Main menu") },
            navigationIcon = {

                IconButton(onClick = {


                }) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }

            }

        )
    },
        bottomBar = {
            TopAppBar(
                title = { Text(text = "Main menu bottom") },
                navigationIcon = {

                    IconButton(onClick = {


                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }

                }

            )
        }
    )
    {
        Box(
            modifier = Modifier.padding(it)
                .background(color = Color.Green)

        ) {


        }


    }


}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScaffoldSrceenPreview() {
    MyApplicationJetpackComposeTheme {

        val context = LocalContext.current
        ScaffoldSrceen()

    }
}
