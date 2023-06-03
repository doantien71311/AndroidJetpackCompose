package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NhanVienViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

): ViewModel() {



}