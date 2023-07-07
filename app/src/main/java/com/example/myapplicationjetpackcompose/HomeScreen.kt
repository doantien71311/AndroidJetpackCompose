package com.example.myapplicationjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

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


        Button(onClick = mainViewModel::henGio2) {


            Text(text = "Hẹn giờ 2")
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
