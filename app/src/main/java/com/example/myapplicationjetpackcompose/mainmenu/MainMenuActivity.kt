package com.example.myapplicationjetpackcompose.mainmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationjetpackcompose.LoginViewModel
import com.example.myapplicationjetpackcompose.NavigationAppHost
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {

    //val m_MainMenuViewModel: MainMenuViewModel by viewModels()

    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   // navHostController = rememberNavController()

                    // NavigationAppHost(navController = navHostController)



                  // CarMenuSrceen(m_MainMenuViewModel)

                   // m_MainMenuViewModel.loadData()
                }
            }
        }
    }
}