package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.myapplicationjetpackcompose.EnumDuyetChungTu
import com.example.myapplicationjetpackcompose.EnumStatus
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmItem
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import com.example.myapplicationjetpackcompose.changeHourMinute
import com.example.myapplicationjetpackcompose.changeYearMonthDay
import com.example.myapplicationjetpackcompose.formatToDateVN
import com.example.myapplicationjetpackcompose.formatToFullTimeVN
import com.example.myapplicationjetpackcompose.formatToTimeDayVN
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.PostData
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.response_data
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.response_ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.model.thoigian
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.services.url_api
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienEvent
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienViewModel
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.ValidateEmail
import com.google.type.Date
import com.google.type.DateTime
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.toJavaLocalDateTime

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

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

    var isAnimatedButtonChon: MutableState<Boolean>  =  mutableStateOf(true)

    var listUngvien : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>())
    var soluongUngVien: Int by mutableStateOf(0)
    var indexUngVien : Int by mutableStateOf(-1)

    var listPhongVan : List<dm_ungvien_cus> by mutableStateOf( mutableListOf<dm_ungvien_cus>())
    var soluongPhongVan: Int by mutableStateOf(0)
    var isShowHenPhongVan: Boolean by mutableStateOf(false)

    var statePhongVan by mutableStateOf(dm_ungvien_cus(
        duyet_henphongvan = EnumDuyetChungTu.DaDuyet,
        ngay_henphongvan = LocalDateTimeGetNow(),
        is_phongvan_online = false,
        is_nhacnho = EnumCoKhong.C,
        link_phongvan_online = "",
        diadiem_henphongvan = ""

    ))

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

            is ThongTinUngVienHenPhongVanEvent.LinkPhongVanOnlineChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    link_phongvan_online = ThongTinUngVienHenPhongVanEvent.link_phongvan_online,
                )



            }

            is ThongTinUngVienHenPhongVanEvent.DiaDiemHenPhongVanChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    diadiem_henphongvan = ThongTinUngVienHenPhongVanEvent.diadiem_henphongvan,
                )



            }

            is ThongTinUngVienHenPhongVanEvent.IsPhongVanOnLineChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    is_phongvan_online = ThongTinUngVienHenPhongVanEvent.is_phongvan_online,
                )

                statePhongVan = if ((statePhongVan.is_phongvan_online)==true){

                    statePhongVan.copy(
                        diadiem_henphongvan = "",
                    )

                } else {

                    statePhongVan.copy(
                        link_phongvan_online = "",
                    )
                }


            }

            is ThongTinUngVienHenPhongVanEvent.IsNhacnhoChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    is_nhacnho = if (ThongTinUngVienHenPhongVanEvent.is_nhacnho) {
                        EnumCoKhong.C
                    } else {
                        EnumCoKhong.K
                    },
                )

                //Số phút nhắc nhở trả về 0
                if (statePhongVan.is_nhacnho == EnumCoKhong.K) {
                    statePhongVan = statePhongVan.copy(
                        sophut_nhacnho = 0
                    )

                }

            }

            is ThongTinUngVienHenPhongVanEvent.SophutNhacNhoOnLineChanged -> {

                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    sophut_nhacnho = ThongTinUngVienHenPhongVanEvent.sophut_nhacnho
                )


            }


            is ThongTinUngVienHenPhongVanEvent.ThoiGianHenPhongVanOnLineChanged -> {

                val ngay_henphongvan = statePhongVan.ngay_henphongvan?.changeHourMinute(
                    ThongTinUngVienHenPhongVanEvent.hour,
                    ThongTinUngVienHenPhongVanEvent.minute,
                )
                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    ngay_henphongvan = ngay_henphongvan
                )

            }

            is ThongTinUngVienHenPhongVanEvent.NgayHenPhongVanOnLineChanged -> {

                val ngay_henphongvan = statePhongVan.ngay_henphongvan?.changeYearMonthDay(
                    ThongTinUngVienHenPhongVanEvent.year,
                    ThongTinUngVienHenPhongVanEvent.month,
                    ThongTinUngVienHenPhongVanEvent.day,
                )
                //Thiết lập giá trị từ ui
                statePhongVan = statePhongVan.copy(
                    ngay_henphongvan = ngay_henphongvan
                )

            }

            else -> {}
        }
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

        //Cho phép hiệu ứng, biến mất
        para_dm_ungvien_cus.isAnimatedVisibility.value = false
        isAnimatedButtonChon.value = true

        //Thêm vào danh sách phỏng vấn, tình trạng đã duyễt hẹn phỏng vấn
        listPhongVan += para_dm_ungvien_cus

        //Thiết lập lại số lượng phỏng vấn
        soluongPhongVan = listPhongVan.size


    }

    fun huyChonPhongVan(para_dm_ungvien_cus: dm_ungvien_cus) {

        //Cho phép xuất hiện lạ trong danh sách ứng viên
        para_dm_ungvien_cus.isAnimatedVisibility.value = true

        //Loại bỏ dòng đã chọn
        this.listPhongVan -= para_dm_ungvien_cus

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

    fun nhacNhoHenPhongVan( dmUngvienCus: dm_ungvien_cus) {

        if (dmUngvienCus.is_nhacnho ?: "" == EnumCoKhong.C) {

            dmUngvienCus.ngay_henphongvan?.let {
                alarmScheduler.scheduleManager(
                    AlarmItem(
                        it.toJavaLocalDateTime()
                            .plusMinutes(-(dmUngvienCus.sophut_nhacnho ?: 0).toLong()),
                        "Đã hẹn nhắc nhở",
                        CommonDataParamater(
                            ma_chucnang = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                            m_title = "Phỏng vấn ${dmUngvienCus.ten_uv ?: ""}",
                            m_text = "Lúc ${dmUngvienCus.ngay_henphongvan?.formatToTimeDayVN()}"
                        )
                    )
                )

            }

        }

    }

    fun xacNhanHenPhongVanGoiEmail() {


        //Tạo dữ liệu data để kết nối api
        var list_dm_ungvien_cus = this.listPhongVan.toList()

        for (item in list_dm_ungvien_cus) {

            item.duyet_henphongvan = statePhongVan.duyet_henphongvan

            item.is_phongvan_online = statePhongVan.is_phongvan_online
            item.link_phongvan_online = statePhongVan.link_phongvan_online
            item.diadiem_henphongvan = statePhongVan.diadiem_henphongvan
            item.ngay_henphongvan = statePhongVan.ngay_henphongvan

            item.is_nhacnho = statePhongVan.is_nhacnho
            item.sophut_nhacnho = statePhongVan.sophut_nhacnho

        }







        //Kết nối api, xác nhận phỏng vấn, gởi email
        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getUngVienHenPhongVanGoiEmail(
                    token = dataStoreServies.getBearToken(),
                    list_dm_ungvien_cus = list_dm_ungvien_cus
                )
                .enqueue(object : Callback<response_data?> {
                    override fun onResponse(
                        call: Call<response_data?>,
                        response: Response<response_data?>
                    ) {

                        response.body()?.let {

                            //Lưu thông tin phỏng vấn thành công thì sẽ hẹn giờ phỏng vấn
                            if ((it.status ?: "") == EnumStatus.OK) {

                                for (item in list_dm_ungvien_cus) {

                                    nhacNhoHenPhongVan(item)

                                }

                            }

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