package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThongTinUngVienViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

): ViewModel(){



}