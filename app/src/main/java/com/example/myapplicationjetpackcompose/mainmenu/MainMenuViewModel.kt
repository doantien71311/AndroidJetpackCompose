package com.example.myapplicationjetpackcompose.mainmenu

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

//@HiltViewModel
class MainMenuViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

    ): ViewModel() {

   // var ListMenuApp: List<dto_menu_app> by mutableListOf<dto_menu_app>()

    //var ListMenuApp : List<dto_menu_app> by mutableListOf<dto_menu_app>()

    fun loadData() {

        GlobalScope.launch {
            RetrofitService.IRetrofitService
                .getHeThongLayDSMenuApp(dataStoreServies.getBearToken(), "ADMIN"  )
                .enqueue(object : Callback<response_dto_menu_app?> {
                    override fun onResponse(
                        call: Call<response_dto_menu_app?>,
                        response: Response<response_dto_menu_app?>
                    ) {

                        //ListMenuApp = response.body()?.data!!
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