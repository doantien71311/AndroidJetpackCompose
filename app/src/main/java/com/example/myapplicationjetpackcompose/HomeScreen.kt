package com.example.myapplicationjetpackcompose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuViewModel
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@Composable
fun HomeScreen(

    navController: NavController,

) {

    val mainViewModel : MainViewModel = hiltViewModel()

    Column (

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
            ) {
        Text (text = "Home screen")

        Button(onClick = mainViewModel::henGio) {
            Text(text = "Hẹn giờ")
        }

        Button(onClick = mainViewModel::showSimpleNotification) {
            Text(text = "Simple Notification")
        }

        Button(onClick = { navController.navigate(Destination.List.route)
        }) {

            Text (text = "to List screen")

        }

        Button(onClick = { navController.navigate(Destination.CarMenu.route)
        }) {

            Text (text = "CarMenu")

        }

        Button(onClick = { navController.navigate(Destination.Login.route)
        }) {

            Text (text = "Login")

        }
        
    }

}
