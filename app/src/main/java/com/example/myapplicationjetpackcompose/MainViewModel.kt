package com.example.myapplicationjetpackcompose

import android.annotation.SuppressLint
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
    private val alarmScheduler: IAlarmScheduler
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
        alarmScheduler.schedule(AlarmItem(LocalDateTime.now().plusSeconds(1),
            "Đã hẹn giờ"))
    }


}