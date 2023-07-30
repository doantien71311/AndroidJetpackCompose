package com.example.myapplicationjetpackcompose.services


import com.example.myapplicationjetpackcompose.model.PostData
import com.example.myapplicationjetpackcompose.model.TokenInfor
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_data
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.response_dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.response_dto_menu_app
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.tb_nhanvien_thietbi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url


interface IRetrofitService {

    @POST(url_api.authenticate_login)
    open fun getToken(@Body postData: PostData): Call<TokenInfor?>

//    @POST(url_api.authenticate_login)
//    open fun getToken(@Body json_au: String): Call<TokenInfor?>

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


    @GET(url_api.dm_ungvien_layds_ungvien_kichhoat)
    open fun getUngVienLayDSUngVienKichHoat(
        @Header("Authorization") token: String,
        @Query("ma_nv") ma_nv: String,
        @Query("tungay") tungay: String,
        @Query("denngay") denngay: String,
    ): Call<response_dm_ungvien_cus?>


    @POST(url_api.dm_ungvien_henphongvan_goiemail)
    open fun getUngVienHenPhongVanGoiEmail(
        @Header("Authorization") token: String,
        @Body list_dm_ungvien_cus: List<dm_ungvien_cus>
    ): Call<response_data?>


    @POST(url_api.dm_ungvien_emailphongvan)
    open fun getUngVienEmailPhongVan(
        @Header("Authorization") token: String,
        @Body dm_ungvien_cus: dm_ungvien_cus
    ): Call<response_data?>

    @POST(url_api.dm_ungvien_henphongvan)
    open fun getUngVienHenPhongVan(
        @Header("Authorization") token: String,
        @Body list_dm_ungvien_cus: List<dm_ungvien_cus>
    ): Call<response_data?>


    //region nhân viên
    @GET(url_api.danhmuc_nhanvien_layds)
    open fun getNhanVienLayDS(
        @Header("Authorization") token: String,
        @Query("ma_nv") ma_nv: String,
        @Query("id") id: String = "",
    ): Call<response_dm_nhanvien_cus?>

    @Multipart
    @POST(url_api.hethong_file_upload_image_nhanvien_daidien)
    fun uploadImageNhanvienDaidien(
        @Header("Authorization") token: String,
        //@Part("file") name: RequestBody?,
        @Part("file") image: MultipartBody.Part,

        ): Call<response_data?>

    //endregion nhân viên



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