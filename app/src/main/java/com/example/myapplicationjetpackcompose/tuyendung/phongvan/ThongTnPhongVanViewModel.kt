package com.example.myapplicationjetpackcompose.tuyendung.phongvan

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.CommonDataParamater
import com.example.myapplicationjetpackcompose.Destination
import com.example.myapplicationjetpackcompose.LocalDateTimeGetNow
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmItem
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import com.example.myapplicationjetpackcompose.formatToParamater
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.response_dm_ungvien_cus
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

//@HiltViewModel
class ThongTnPhongVanViewModel @AssistedInject constructor (
    private val dataStoreServies: IDataStoreServies,
    private val alarmScheduler: IAlarmScheduler,
    @Assisted("pKeyvalue") pKeyvalue: String,
    @Assisted("pTungay") pTungay: String,
    @Assisted("pDenngay") pDenngay: String,
): ViewModel()
{

    @AssistedFactory
    interface Factory {

        fun create(

            @Assisted("pKeyvalue") pKeyvalue: String,
            @Assisted("pTungay") pTungay: String,
            @Assisted("pDenngay") pDenngay: String,

            ): ThongTnPhongVanViewModel

    }

    companion object {

        fun providerMainViewModelFactory(
            factory: ThongTnPhongVanViewModel.Factory,
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

    var tungay: kotlinx.datetime.LocalDateTime by mutableStateOf(LocalDateTimeGetNow())
    var denngay: kotlinx.datetime.LocalDateTime by mutableStateOf(LocalDateTimeGetNow())
    var keyvalue: String by mutableStateOf("")

    var listPhongVan : List<dm_ungvien_cus> by mutableStateOf( mutableListOf<dm_ungvien_cus>())
    var soluongPhongVan: Int by mutableStateOf(0)

    init {

        //Lấy các giá trị defalue
        this.denngay = kotlinx.datetime.LocalDateTime(2000,12,31,23,59)
        if (!pTungay.isNullOrEmpty()){
            this.tungay = kotlinx.datetime.LocalDateTime.parse(pTungay)
        }

        this.denngay = kotlinx.datetime.LocalDateTime(2023,12,31,23,59)
        if (!pDenngay.isNullOrEmpty()) {

            this.denngay = kotlinx.datetime.LocalDateTime.parse(pDenngay)
        }

        this.keyvalue = pKeyvalue

        loadData()

    }

    fun loadData() {

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getUngVienLayDSUngVienKichHoat(
                    dataStoreServies.getBearToken(),
                    dataStoreServies.getMaNV(),
                    tungay.formatToParamater(),
                    denngay.formatToParamater()
                )
                .enqueue(object : Callback<response_dm_ungvien_cus?> {
                    override fun onResponse(
                        call: Call<response_dm_ungvien_cus?>,
                        response: Response<response_dm_ungvien_cus?>
                    ) {

                        response.body()?.data?.let {


                            listPhongVan = it

                        }



                        //Thiết lập các giá trị măc định
                        soluongPhongVan = listPhongVan.size

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

    fun henGio() {

        alarmScheduler.scheduleManager(
            AlarmItem(
                LocalDateTime.now().plusSeconds(3),
            "Đã hẹn giờ",
            CommonDataParamater (
                ma_chucnang = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                m_title = "Đã hẹn giờ tile",
                m_text = "Đã hẹn giờ text",
            )
        )
        )

    }

    fun henGio2() {

        alarmScheduler.scheduleManager(
            AlarmItem(
                LocalDateTime.now().plusSeconds(3),
                "Đã hẹn giờ",
                CommonDataParamater (
                    ma_chucnang = MainMenuDestination.NHAPLIEU_NhanSu_ThongTinPhongVan_Duyet.route,
                    m_title = "Đã hẹn giờ tile 2",
                    m_text = "Đã hẹn giờ text 2",
                )
            )
        )

    }



}

