package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.content.Context
import android.util.Log
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
import com.example.myapplicationjetpackcompose.model.response_dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.response_dto_menu_app
import com.example.myapplicationjetpackcompose.services.DataStoreCustomServices
import com.example.myapplicationjetpackcompose.services.DataStoreServices
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        loadData()

    }

    private fun loadData() {

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getNhanVienLayDS(dataStoreServies.getBearToken(), dataStoreServies.getUserName()  )
                .enqueue(object : Callback<response_dm_nhanvien_cus?> {
                    override fun onResponse(
                        call: Call<response_dm_nhanvien_cus?>,
                        response: Response<response_dm_nhanvien_cus?>
                    ) {
                        response.body()?.data?.let {
                            state = it
                        }

                    }

                    override fun onFailure(
                        call: Call<response_dm_nhanvien_cus?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }
    }

    private fun saveData() {


    }





}