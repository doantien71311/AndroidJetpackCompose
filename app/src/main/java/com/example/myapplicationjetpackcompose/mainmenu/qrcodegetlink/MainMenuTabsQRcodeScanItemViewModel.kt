package com.example.myapplicationjetpackcompose.mainmenu.qrcodegetlink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuTabsQRcodeScanItemViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies,
)
: ViewModel()
{
    var isShow: Boolean by mutableStateOf(false)
}