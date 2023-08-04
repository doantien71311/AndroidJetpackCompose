package com.example.myapplicationjetpackcompose.mainmenu


sealed class MainMenuEvent {

    object LoadData : MainMenuEvent()

    object SaveData : MainMenuEvent()

}