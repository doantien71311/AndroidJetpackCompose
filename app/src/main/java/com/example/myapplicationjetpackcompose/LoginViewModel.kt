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
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.services.PostData
import com.example.myapplicationjetpackcompose.services.RetrofitCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreServies: IDataStoreServies
): ViewModel() {

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

    fun LoadData() {



            getToKen()




    }


     fun KiemTra_NSD()
    {
        GlobalScope.launch {

        RetrofitService.IRetrofitService
            .getEncryptDES(dataStoreServies.getBearToken(), _mat_khau.value!!)
            .enqueue(object : Callback<response_EncryptDES?> {
                override fun onResponse(
                    call: Call<response_EncryptDES?>,
                    response: Response<response_EncryptDES?>
                ) {

                    val matkhau_mahoa = response.body()?.data

                    var _ht_dm_nsd = ht_dm_nsd(_ma_nsd.value, matkhau_mahoa)

                    GlobalScope.launch {


                        RetrofitService.IRetrofitService
                            .getKiemTra_NSD(dataStoreServies.getBearToken().toString(), _ht_dm_nsd)
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
    }



   private suspend fun LayThongTinDoanhNghiep() {


       RetrofitService.IRetrofitService
            .getThongTinDoanhNghiep(dataStoreServies.getBearToken())
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


        RetrofitService
            .IRetrofitService
            .getToken(PostData())
            .enqueue(object : Callback<TokenInfor?> {
                override fun onResponse(
                    call: Call<TokenInfor?>,
                    response: Response<TokenInfor?>
                ) {

                    GlobalScope.launch {

                        dataStoreServies.saveAuthToken(response.body()?.token.toString())

                        LayThongTinDoanhNghiep()
                    }


                }

                override fun onFailure(call: Call<TokenInfor?>, t: Throwable) {

                    var s = t.localizedMessage
                    Log.e("Faild", t.message.toString())
                }

            })


    }


}