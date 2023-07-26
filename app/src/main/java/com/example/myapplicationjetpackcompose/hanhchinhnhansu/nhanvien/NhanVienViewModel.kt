package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.EnumCoKhong
import com.example.myapplicationjetpackcompose.EnumDuyetChungTu
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuViewModel
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.services.DataStoreCustomServices
import com.example.myapplicationjetpackcompose.services.DataStoreServices
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class NhanVienViewModel @Inject constructor (
//  //  private val dataStoreServies: IDataStoreServies
//    // @ApplicationContext context: Context,
//    private val  context: Context
//    ): ViewModel() {
//
//    ///var lookupChucVu: LookupChucVuViewModel = LookupChucVuViewModel(dataStoreServies)
//
//    //private val dataStoreServies : IDataStoreServies = DataStoreServices(context)
//
//
//}

@HiltViewModel
class NhanVienViewModel @Inject constructor (
      private val dataStoreServies: IDataStoreServies
): ViewModel() {

    var state : List<dm_nhanvien_cus> by mutableStateOf(mutableListOf<dm_nhanvien_cus>(dm_nhanvien_cus()))
    var token: String by mutableStateOf("token")

    fun onEvent(event: NhanVienEvent) {
        when (event) {

            is NhanVienEvent.LoadData -> {

                loadData()
            }

            is NhanVienEvent.SaveData -> {

                saveData()
            }

        }

    }



    init {


    }

    private fun loadData() {


    }

    private fun saveData() {


    }





}