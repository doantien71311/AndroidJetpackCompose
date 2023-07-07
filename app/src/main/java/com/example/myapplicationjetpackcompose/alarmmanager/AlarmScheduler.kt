package com.example.myapplicationjetpackcompose.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.myapplicationjetpackcompose.ParcelizeDataParamater
import com.example.myapplicationjetpackcompose.EnumParcelizeDataParamater
import java.time.ZoneId

class AlarmScheduler (

    private val context: Context

        ) : IAlarmScheduler
{

    private val alarmManager = context.getSystemService(AlarmManager::class.java)


    override fun scheduleManager(item: AlarmItem) {

        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {

            val dataParamater = ParcelizeDataParamater(
                ma_chucnang = item.CommonDataParamater.ma_chucnang,
                tungay = item.CommonDataParamater.tungay,
                denngay = item.CommonDataParamater.denngay,
                key_id = item.CommonDataParamater.key_id,
                array_id = item.CommonDataParamater.array_id,
                m_text = item.CommonDataParamater.m_text,
                m_title = item.CommonDataParamater.m_title
            )
            putExtra(EnumParcelizeDataParamater.ParcelizeData, dataParamater)
        }

        val requestCode = System.currentTimeMillis().toInt()
        val pendingIntentNotification = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 500,
            pendingIntentNotification

        )
    }

    override fun cancelManager(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

        )
    }


}