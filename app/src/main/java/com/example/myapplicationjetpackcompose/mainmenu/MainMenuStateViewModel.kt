package com.example.myapplicationjetpackcompose.mainmenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuStateViewModel @Inject constructor (
 private val dataStoreServies: IDataStoreServies
): ViewModel() {

// var isAuth : Boolean by mutableStateOf(false)
 var isAuth :  Boolean by mutableStateOf(false)

 init {

  viewModelScope.launch {

   val token = dataStoreServies.getToken()
   isAuth = token != ""

  }


 }
}