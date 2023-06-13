package com.example.myapplicationjetpackcompose.mainmenu

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep

import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

import com.example.myapplicationjetpackcompose.model.response_dto_menu_app

import kotlinx.coroutines.delay

@HiltViewModel
class MainMenuViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

    ): ViewModel() {

    //Cách 1 đang ok
    var ListMenuApp : List<dto_menu_app> by mutableStateOf(mutableListOf<dto_menu_app>(dto_menu_app()))
    var isLoadding : Boolean by mutableStateOf(true)
    var abc :  String by mutableStateOf("")

    //Cách 2, chưa làm được
    //var ListMenuApp : List<dto_menu_app> = mutableListOf()



    fun loadData() {

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getHeThongLayDSMenuApp(dataStoreServies.getBearToken(), "ADMIN"  )
                .enqueue(object : Callback<response_dto_menu_app?> {
                    override fun onResponse(
                        call: Call<response_dto_menu_app?>,
                        response: Response<response_dto_menu_app?>
                    ) {

                        ListMenuApp = response.body()?.data!!
                        isLoadding = false


                    }

                    override fun onFailure(
                        call: Call<response_dto_menu_app?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }

    }

}