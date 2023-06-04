package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

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
class ThongTinUngVienViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

): ViewModel() {

    var indexUngVien : Int by mutableStateOf(-1)

    fun loadData() {
        viewModelScope.launch {
            indexUngVien = 99
        }


    }
}