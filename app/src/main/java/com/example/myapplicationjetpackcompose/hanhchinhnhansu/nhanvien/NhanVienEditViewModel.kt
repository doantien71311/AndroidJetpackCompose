package com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.model.dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.model.response_data
import com.example.myapplicationjetpackcompose.model.response_dm_nhanvien_cus
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class NhanVienEditViewModel @AssistedInject constructor (
    private val dataStoreServies: IDataStoreServies,
    @Assisted("pKeyvalue") pKeyvalue: String,
): ViewModel()
{



    @AssistedFactory
    interface Factory {

        fun create(

            @Assisted("pKeyvalue") pKeyvalue: String,

            ): NhanVienEditViewModel

    }

    companion object {

        fun providerMainViewModelFactory(
            factory: Factory,
            pKevalue: String,

            ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {

                    return factory.create(pKevalue) as T

                }

            }
        }

    }


    var state : dm_nhanvien_cus by mutableStateOf(dm_nhanvien_cus())

    var kevalue: String by mutableStateOf("")


    fun onEvent(event: NhanVienEditEvent) {
        when (event) {

            is NhanVienEditEvent.LoadData -> {

                loadData(this.kevalue)
            }

            is NhanVienEditEvent.SaveData -> {

                saveData()
            }

        }

    }

    init {

        this.kevalue = pKeyvalue
        loadData(this.kevalue)

    }

    private fun loadData(id: String) {

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getNhanVienLayDS(dataStoreServies.getBearToken(), dataStoreServies.getUserName(), id  )
                .enqueue(object : Callback<response_dm_nhanvien_cus?> {
                    override fun onResponse(
                        call: Call<response_dm_nhanvien_cus?>,
                        response: Response<response_dm_nhanvien_cus?>
                    ) {
                        response.body()?.data?.let {
                            state = it[0]
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

    private fun uploadImageNhanvienDaidien(file: File) {

        // Pass it like this
       // val file = File(RealPathUtils.getRealPathFromURI_API19(context, uri))
        //val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        // MultipartBody.Part is used to send also the actual file name
        val body: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            file.name,
         //   requestFile

            file.asRequestBody()
        )

        // Add another part within the multipart request
       // val fullName: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .uploadImageNhanvienDaidien(
                    token = dataStoreServies.getBearToken(),
                    image = body
                )
                .enqueue(object : Callback<response_data?> {
                    override fun onResponse(
                        call: Call<response_data?>,
                        response: Response<response_data?>
                    ) {
                        response.body()?.data?.let {
                            state.hinhanh_daidien_url = it
                        }

                    }

                    override fun onFailure(
                        call: Call<response_data?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }

    }


}