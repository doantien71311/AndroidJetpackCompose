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
import com.example.myapplicationjetpackcompose.CommonDataParamater
import com.example.myapplicationjetpackcompose.EnumCoKhong
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmItem
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import com.example.myapplicationjetpackcompose.formatToDateVN
import com.example.myapplicationjetpackcompose.formatToFullTimeVN
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienViewModel
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ValidateEmail
import com.google.type.Date
import com.google.type.DateTime
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDateTime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

//@HiltViewModel
class ThongTinUngVienViewModel @AssistedInject constructor (
    private val dataStoreServies: IDataStoreServies,
    private val alarmScheduler: IAlarmScheduler,
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
    var soluongPhongVan: Int by mutableStateOf(0)
    var isShowHenPhongVan: Boolean by mutableStateOf(false)

    var statePhongVan by mutableStateOf(dm_ungvien_cus())
    sealed class ValidationEvent {
        object Success : ValidationEvent()

    }
    private val validationEventChannel = Channel<ThongTinUngVienViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    private val validateEmail : ValidateEmail = ValidateEmail()
    private var emailResult = validateEmail.execute(statePhongVan.email?:"")
    fun handleEvent(ThongTinUngVienHenPhongVanEvent: ThongTinUngVienHenPhongVanEvent) {

        when (ThongTinUngVienHenPhongVanEvent) {

            is ThongTinUngVienHenPhongVanEvent.EmailChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    email = ThongTinUngVienHenPhongVanEvent.emailAddress,
                )

                //Hiển thị lỗi email nếu có
                emailResult = validateEmail.execute(statePhongVan.email?:"")
                statePhongVan = statePhongVan.copy(
                    is_email_error = emailResult.isError,
                    message_email_error = emailResult.errorMessage
                )

            }
        }
    }



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

    fun nhacNho( dmUngvienCus: dm_ungvien_cus) {

        if (dmUngvienCus.is_nhacnho ?: "" == EnumCoKhong.C) {

            dmUngvienCus.ngay_henphongvan?.let {
                alarmScheduler.scheduleManager(
                    AlarmItem(
                        it.toJavaLocalDateTime().plusMinutes((dmUngvienCus.sophut_nhacnho ?: 0).toLong()),
                        "Đã hẹn nhắc nhở",
                        CommonDataParamater(
                            ma_chucnang = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                            m_title = dmUngvienCus.ten_uv?:"",
                            m_text =  dmUngvienCus.ngay_henphongvan!!.formatToFullTimeVN()
                        )
                    )
                )

            }

        }

    }

    fun xacNhanHenPhongVan() {


        var list_dm_ungvien_cus =  this.listPhongVan.toMutableList()

        //Kết nối api
        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getUngVienHenPhongVanGoiEmail(
                    token = dataStoreServies.getBearToken(),
                    list_dm_ungvien_cus = list_dm_ungvien_cus
                )
                .enqueue(object : Callback<response_dm_ungvien_cus?> {
                    override fun onResponse(
                        call: Call<response_dm_ungvien_cus?>,
                        response: Response<response_dm_ungvien_cus?>
                    ) {

                        response.body()?.data?.let {
                            val response_data = response.body()?.data
                        }

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