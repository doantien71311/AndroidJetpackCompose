package com.example.myapplicationjetpackcompose.services


import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_paramater
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_data
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dto_menu_app
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.tb_nhanvien_thietbi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

class PostData {
    val Username: String = url_api.username
    val Password: String = url_api.password
}


interface IRetrofitService {

    @POST(url_api.authenticate_login)
    open fun getToken(@Body postData: PostData): Call<TokenInfor?>

    @POST(url_api.authenticate_encryptDES)
    open fun getEncryptDES(@Header("Authorization") token: String, @Body string: String): Call<response_EncryptDES?>

    @POST(url_api.hethong_nguoisudung_kiemtra_nsd)
    open fun getKiemTra_NSD(@Header("Authorization") token: String, @Body value: ht_dm_nsd): Call<response_boolean?>


    @GET(url_api.thongtin_doanhnghiep_datatable)
    open fun getThongTinDoanhNghiep(@Header("Authorization") token: String): Call<response_ht_thongtinhdoanhnghiep?>


    @GET(url_api.hethong_layds_menu_app)
    open fun getHeThongLayDSMenuApp(@Header("Authorization") token: String,
                                       @Query("ma_nsd") ma_nsd: String): Call<response_dto_menu_app?>

    @GET(url_api.thongtin_doanhnghiep_datatable)
    open fun getThongTinDoanhNghiepV2(): Call<response_ht_thongtinhdoanhnghiep?>


    @GET(url_api.danhsach_danhmuc_chucvu_datatable)
    open fun getDanhMucChucVu(
        @Header("Authorization") token: String,
        @Query("ma_chucvu") ma_chucvu: String
    ): Call<response_dm_chucvu_ds?>





    @POST(url_api.tb_nhanvien_thietbi_insert)
    open fun tb_nhanvien_thietbi_insert(
        @Header("Authorization") token: String,
        @Body data: tb_nhanvien_thietbi,
        @QueryMap params: Map<String, String>
    ): Call<response_data?>



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