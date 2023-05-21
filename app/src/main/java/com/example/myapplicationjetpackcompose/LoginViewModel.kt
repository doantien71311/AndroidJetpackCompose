package com.example.myapplicationjetpackcompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.services.HttpClientService
import com.example.myapplicationjetpackcompose.services.PostData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel: ViewModel() {

    var row_ht_thongtindoanhnghiep: ht_thongtinhdoanhnghiep by mutableStateOf(ht_thongtinhdoanhnghiep())

    fun layThongTinDoanhNhgiep() {

        getToKen()
    }



   private fun LayThongTinDoanhNghiep(token: String) {

        HttpClientService.IHttpClientService
            .getThongTinDoanhNghiep(token)
            .enqueue(object : Callback<response_ht_thongtinhdoanhnghiep?> {
                override fun onResponse(
                    call: Call<response_ht_thongtinhdoanhnghiep?>,
                    response: Response<response_ht_thongtinhdoanhnghiep?>
                ) {

                    row_ht_thongtindoanhnghiep = response.body()?.data!!


                }

                override fun onFailure(
                    call: Call<response_ht_thongtinhdoanhnghiep?>,
                    t: Throwable
                ) {

                    var s = t.localizedMessage
                    Log.e("Faild", t.message.toString())
                }

            })
    }


    private fun getToKen() {

        HttpClientService.IHttpClientService.getToken(PostData())
            .enqueue(object : Callback<TokenInfor?> {
                override fun onResponse(
                    call: Call<TokenInfor?>,
                    response: Response<TokenInfor?>
                ) {

                    var token = "Bearer " + response.body()?.token.toString()
                    LayThongTinDoanhNghiep(token)

                }

                override fun onFailure(call: Call<TokenInfor?>, t: Throwable) {

                    var s = t.localizedMessage
                    Log.e("Faild", t.message.toString())
                }

            })


    }

}