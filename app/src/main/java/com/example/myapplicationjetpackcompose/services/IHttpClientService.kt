package com.example.myapplicationjetpackcompose.services


import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

class PostData {
    val Username: String = url_api.username
    val Password: String = url_api.password
}


interface IHttpClientService {

    @POST(url_api.authenticate_login)
    open fun getToken(@Body postData: PostData): Call<TokenInfor?>

    @POST(url_api.authenticate_encryptDES)
    open fun getEncryptDES(@Header("Authorization") token: String, @Body string: String): Call<response_EncryptDES?>

    @POST(url_api.hethong_nguoisudung_kiemtra_nsd)
    open fun getKiemTra_NSD(@Header("Authorization") token: String, @Body value: ht_dm_nsd): Call<response_boolean?>


    @GET(url_api.thongtin_doanhnghiep_datatable)
    open fun getThongTinDoanhNghiep(@Header("Authorization") token: String): Call<response_ht_thongtinhdoanhnghiep?>


//    JvmSuppressWildcards

    @GET
    open fun <T> getDataRowByPara(@Header("Authorization") token: String, @Url url_api: String, @FieldMap params: Map<String?, String?>): Call<T?>

//    Map<String, String> params = new HashMap<String, String>();
//    params.put("key1", "val1");
//    params.put("key2", "val2");

    @GET("/maps/api/geocode/json")
    @FormUrlEncoded
    fun getPositionByZip(@FieldMap params: Map<String?, String?>) : Call<response_ht_thongtinhdoanhnghiep?>
}