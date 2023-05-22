package com.example.myapplicationjetpackcompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.services.HttpClientService
import com.example.myapplicationjetpackcompose.services.PostData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel: ViewModel() {

    var row_ht_thongtindoanhnghiep: ht_thongtinhdoanhnghiep by mutableStateOf(ht_thongtinhdoanhnghiep())
    var row_ht_dm_nsd: ht_dm_nsd  by mutableStateOf(ht_dm_nsd())

    private val _ma_nsd = MutableLiveData<String>()
    val ma_nsd : LiveData<String> = _ma_nsd

    private val _mat_khau = MutableLiveData<String>()
    val mat_khau : LiveData<String> = _mat_khau


    private val _login_enable = MutableLiveData<Boolean>()
    val login_enable : LiveData<Boolean> = _login_enable


     fun onMaNsdChanged(ma_nsd: String)
     {
         _ma_nsd.value = ma_nsd.uppercase()

     }

    fun onMatKhauChanged(matkhau: String)
    {
        _mat_khau.value = matkhau

    }

    fun layThongTinDoanhNhgiep() {

        getToKen()
    }


    fun KiemTra_NSD()
    {
        var _token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3NDg3N0RGNjIzOERGMjMzQSIsIlNxbF9EYXRhYmFzZV9OYW1lIjoiQ0QxQ0Q2NTRCRkRBNEQ2NzMxMkU4QkUwNzg3RUM5OUEiLCJTcWxfVXNlcl9OYW1lIjoiQ0QxQ0Q2NTRCRkRBNEQ2NzMxMkU4QkUwNzg3RUM5OUEiLCJTcWxfUGFzc3dvcmQiOiIyNzI3NEQyM0NENzc0N0UxM0Q0ODhCQzU0REY0QkFBNDgwMkQ1OEE0OTJBMUM3MUEiLCJtX1VzZXJJZCI6IkI4QkFDNkIwNDk3MDlCMTM0QUI0QjU0RjU3RDlFMUMwREIwNjhFOTEwOTFCMTA0NyIsImp0aSI6IjRmOGEzOGMyLWNhNzYtNDJiMC1iYmRiLWE2OGViMWI3MTI4ZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTk5MjEiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjQyMDAifQ.UCzGPYTrQNZ4RFKdkHFLp4XGuAX03-sqXCWf6Sh9icw"

        var token = "Bearer "+_token


        HttpClientService.IHttpClientService
            .getEncryptDES(token, _mat_khau.value!!)
            .enqueue(object : Callback<response_EncryptDES?> {
                override fun onResponse(
                    call: Call<response_EncryptDES?>,
                    response: Response<response_EncryptDES?>
                ) {

                    val matkhau_mahoa = response.body()?.data

                    var _ht_dm_nsd = ht_dm_nsd(_ma_nsd.value, matkhau_mahoa)

                    HttpClientService.IHttpClientService
                        .getKiemTra_NSD(token, _ht_dm_nsd)
                        .enqueue(object : Callback<response_boolean?> {
                            override fun onResponse(
                                call: Call<response_boolean?>,
                                response: Response<response_boolean?>
                            ) {

                                _login_enable.value = response.body()?.data

                            }

                            override fun onFailure(
                                call: Call<response_boolean?>,
                                t: Throwable
                            ) {

                                var s = t.localizedMessage
                                Log.e("Faild", t.message.toString())
                            }

                        })


                }

                override fun onFailure(
                    call: Call<response_EncryptDES?>,
                    t: Throwable
                ) {

                    var s = t.localizedMessage
                    Log.e("Faild", t.message.toString())
                }

            })



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