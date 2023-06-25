package com.example.myapplicationjetpackcompose.tuyendung.phongvan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.CommonDataParamater
import com.example.myapplicationjetpackcompose.Destination
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmItem
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuDestination
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ThongTnPhongVanViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies,
    private val alarmScheduler: IAlarmScheduler,
): ViewModel()
{


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

