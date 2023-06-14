package com.example.myapplicationjetpackcompose

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmItem
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmScheduler
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
    private val alarmScheduler: IAlarmScheduler,
  //  private val context: Context
) : ViewModel() {

    @SuppressLint("MissingPermission")
    fun showSimpleNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }

    @SuppressLint("MissingPermission")
    fun updateSimpleNotification() {
        notificationManager.notify(1, notificationBuilder
            .setContentTitle("NEW TITLE")
            .build()
        )
    }

    fun cancelSimpleNotification() {
        notificationManager.cancel(1)
    }
    fun henGio() {

        alarmScheduler.scheduleManager(AlarmItem(LocalDateTime.now().plusSeconds(3),
            "Đã hẹn giờ",
             CommonDataParamater (
                 ma_chucnang = Destination.List.route,
                 m_title = "Đã hẹn giờ tile",
                 m_text = "Đã hẹn giờ text",
                     )
            )
        )

    }

    fun henGio2() {

        alarmScheduler.scheduleManager(AlarmItem(LocalDateTime.now().plusSeconds(5),
            "Đã hẹn giờ",
            CommonDataParamater (
                ma_chucnang = Destination.CarMenu.route,
                m_title = "Đã hẹn giờ tile 2",
                m_text = "Đã hẹn giờ text 2",
            )
        )
        )

    }


}