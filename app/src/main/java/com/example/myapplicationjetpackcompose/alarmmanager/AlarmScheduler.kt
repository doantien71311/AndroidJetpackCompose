package com.example.myapplicationjetpackcompose.alarmmanager

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.ZoneId

class AlarmScheduler (

    private val context: Context

        ) : IAlarmScheduler
{

    private val alarmManager = context.getSystemService(AlarmManager::class.java)


    override fun schedule(item: AlarmItem) {


        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {
            putExtra("EXTRA_MESSAGE",item.message)
        }

       // val intentNotification = Intent (context, Notification::class.java)
        val pendingIntentNotification = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 500,
            pendingIntentNotification,
//            PendingIntent.getBroadcast(
//                context,
//                item.hashCode(),
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
//            )

        )





    }

    override fun cancel(item: AlarmItem) {
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