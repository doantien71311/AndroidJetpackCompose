package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dm_ungvien_cus
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienViewModel
import com.google.type.Date
import com.google.type.DateTime
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@HiltViewModel
class ThongTinUngVienViewModel @AssistedInject constructor (
    private val dataStoreServies: IDataStoreServies,
    @Assisted("pKeyvalue") pKeyvalue: String,
    @Assisted("pTungay") pTungay: String,
    @Assisted("pDenngay") pDenngay: String,
): ViewModel() {

    @AssistedFactory
    interface Factory {

        fun create(

            @Assisted("pKeyvalue") pKeyvalue: String,
            @Assisted("pTungay") pTungay: String,
            @Assisted("pDenngay") pDenngay: String,

            ): ThongTinUngVienViewModel

    }
    companion object {

        fun providerMainViewModelFactory(
            factory: Factory,
            pKevalue: String,
            pTungay: String,
            pDenngay: String,

            ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {

                    return factory.create(pKevalue, pTungay, pDenngay) as T

                }

            }
        }

    }

    var tungay: String by mutableStateOf("")
    var denngay: String by mutableStateOf("")
    var kevalue: String by mutableStateOf("")


    var listUngvien : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>())
    var soluongUngVien: Int by mutableStateOf(0)
    var indexUngVien : Int by mutableStateOf(-1)

    var listPhongVan : List<dm_ungvien_cus> by mutableStateOf( mutableListOf<dm_ungvien_cus>())

    //var listPhongVan : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>(dm_ungvien_cus()))
    var soluongPhongVan: Int by mutableStateOf(0)
    var isShowHenPhongVan: Boolean by mutableStateOf(false)

    private val _diadiem_henphongvan = MutableLiveData<String>()
    val diadiem_henphongvan : LiveData<String> = _diadiem_henphongvan
    fun onChangedDiaDiemHenPhongVan(diadiem_henphongvan: String)
    {
        _diadiem_henphongvan.value = diadiem_henphongvan

    }

    private val _link_phongvan_online = MutableLiveData<String>()
    val link_phongvan_online : LiveData<String> = _link_phongvan_online
    fun onChangedLinkPhongVanOnline(link_phongvan_online: String)
    {
        _link_phongvan_online.value = link_phongvan_online

    }

    private val _is_phongvan_online = MutableLiveData<Boolean>()
    val is_phongvan_online : LiveData<Boolean> = _is_phongvan_online
    fun onChangedIsPhongVanOnline(is_phongvan_online: Boolean) {
        _is_phongvan_online.value = is_phongvan_online

    }



    init {

        //Lấy các giá trị defalue
        this.tungay = pTungay
        this.denngay = pDenngay
        this.kevalue = pKeyvalue

        this.tungay = "1990-01-01"
        this.denngay = "2023-12-31"

        loadData()

    }


    fun chonPhongVan(para_dm_ungvien_cus: dm_ungvien_cus) {


        //Cho phép hiệu ứng
        para_dm_ungvien_cus.isAnimatedVisibility.value = false

        //Loại bỏ dòng đã chọn
        this.listUngvien.drop(this.listUngvien.indexOf(para_dm_ungvien_cus))


        //Thêm vào danh sách phỏng vấn
        listPhongVan += para_dm_ungvien_cus

        //Thiết lập lại số lượng phỏng vấn
        soluongPhongVan = listPhongVan.size



    }

//    fun DateTime.setDayTime(hourOfDay: Int? = null, minuteOfHour: Int? = null, secondOfMinute: Int? = null) {
//        var dateTime = this
//        if(hourOfDay != null) {
//            dateTime = dateTime.hourOfDay().setCopy(hourOfDay)
//        }
//        if(minuteOfHour != null) {
//            dateTime = dateTime.minuteOfHour().setCopy(minuteOfHour)
//        }
//        if(secondOfMinute != null) {
//            dateTime = dateTime.secondOfMinute().setCopy(secondOfMinute)
//        }
//        return dateTime
//    }

    fun loadData() {


        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getUngVienLayDSUngVienKichHoat(
                        dataStoreServies.getBearToken(),
                        dataStoreServies.getMaNV(),
                        tungay.toString(),
                        denngay.toString()
                )
                .enqueue(object : Callback<response_dm_ungvien_cus?> {
                    override fun onResponse(
                        call: Call<response_dm_ungvien_cus?>,
                        response: Response<response_dm_ungvien_cus?>
                    ) {

                        response.body()?.data?.let {


                            listUngvien = it

                        }

                        listPhongVan = mutableListOf<dm_ungvien_cus>()
                        //Thiết lập các giá trị măc định
                        soluongPhongVan = listPhongVan.size
                        soluongUngVien = listUngvien.size
                    }

                    override fun onFailure(
                        call: Call<response_dm_ungvien_cus?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }







    }
}