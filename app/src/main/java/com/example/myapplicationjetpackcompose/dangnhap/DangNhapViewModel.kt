package com.example.myapplicationjetpackcompose.dangnhap

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.PostData
import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.services.IDataStoreServies

import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.services.url_api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@HiltViewModel
class DangNhapViewModel @Inject constructor(
    private val dataStoreServies: IDataStoreServies,
): ViewModel() {

     var row_ht_thongtindoanhnghiep: ht_thongtinhdoanhnghiep by mutableStateOf(ht_thongtinhdoanhnghiep())

    var row_ht_dm_nsd: ht_dm_nsd by mutableStateOf(ht_dm_nsd())

    private val _ma_nsd = MutableLiveData<String>()
    val ma_nsd : LiveData<String> = _ma_nsd

    private val _mat_khau = MutableLiveData<String>()
    val mat_khau : LiveData<String> = _mat_khau

    var login_enable : Boolean by mutableStateOf(false)

    var isValidation : Boolean by mutableStateOf(true)
    var messs : String by mutableStateOf("")

    fun onMaNsdChanged(ma_nsd: String)
    {
        _ma_nsd.value = ma_nsd.uppercase()

    }

    fun onMatKhauChanged(matkhau: String)
    {
        _mat_khau.value = matkhau

    }

    init {

        getToKen()

    }

    fun KiemTra_NSD()
    {
        isValidation = true
        messs = ""

        if (this._mat_khau.value.isNullOrBlank())
        {
            isValidation = false
            messs = "Mật khẩu trống"
            return
        }


        viewModelScope.launch {

        RetrofitService.IRetrofitService
            .getEncryptDES(dataStoreServies.getBearToken(), _mat_khau.value?:"")
            .enqueue(object : Callback<response_EncryptDES?> {
                override fun onResponse(
                    call: Call<response_EncryptDES?>,
                    response: Response<response_EncryptDES?>
                ) {

                    val matkhau_mahoa = response.body()?.data

                    var _ht_dm_nsd = ht_dm_nsd(_ma_nsd.value, matkhau_mahoa)

                    viewModelScope.launch {


                        RetrofitService.IRetrofitService
                            .getKiemTra_NSD(dataStoreServies.getBearToken().toString(), _ht_dm_nsd)
                            .enqueue(object : Callback<response_boolean?> {
                                override fun onResponse(
                                    call: Call<response_boolean?>,
                                    response: Response<response_boolean?>
                                ) {

                                    login_enable = response.body()?.data ?: false
                                    viewModelScope.launch {
                                        if (login_enable) {
                                            dataStoreServies.saveUserName(_ht_dm_nsd.ma_nsd!!)
                                            dataStoreServies.savePassWord(_ht_dm_nsd.matkhau!!)
                                            dataStoreServies.saveMaNV(_ht_dm_nsd.ma_nsd!!)
                                        }
                                    }

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

        try {

           var json_au = "  {\n" +
                   "      \"Username\" : \"B8BAC6B049709B134AB4B54F57D9E1C0DB068E91091B1047\",\n" +
                   "       \"Password\" : \"6CC0BCCBEA7D5E8F678F14FC5400E251\"\n" +
                   "  }"

            val postData = PostData(url_api.username, url_api.password)
            val json = Json {encodeDefaults = true

            }.encodeToString(PostData.serializer(), postData)
            val json_js = Json.encodeToString(serializer<PostData>(), postData)

            RetrofitService
                .IRetrofitService
                .getToken(postData)
                .enqueue(object : Callback<TokenInfor?> {
                    override fun onResponse(
                        call: Call<TokenInfor?>,
                        response: Response<TokenInfor?>
                    ) {

                        if (response.code() == 400) {
                            Log.v("Error code 400", response.errorBody().toString());
                        }

                        viewModelScope.launch {

                            dataStoreServies.saveKeyToken(response.body()?.token.toString())

                            LayThongTinDoanhNghiep()
                        }


                    }

                    override fun onFailure(call: Call<TokenInfor?>, t: Throwable) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })
        }

        catch (e: Exception) {
            Log.e("Faild", e.message.toString())
        }
    }




    }



