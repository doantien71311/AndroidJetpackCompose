package com.example.myapplicationjetpackcompose.alarmmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.myapplicationjetpackcompose.ParcelizeDataParamater
import com.example.myapplicationjetpackcompose.EnumChannel
import com.example.myapplicationjetpackcompose.EnumDeepLink
import com.example.myapplicationjetpackcompose.EnumParcelizeDataParamater
import com.example.myapplicationjetpackcompose.R
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuActivity

class AlarmReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context, intent: Intent) {

//        val message = intent?.getStringExtra("EXTRA_MESSAGE")
//        if (message != null) {
//            Toast.makeText(
//                context,
//                message,
//                Toast.LENGTH_LONG
//            ).show()
//        }

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0

        val parcelizeDataParamater: ParcelizeDataParamater =
        if (intent.hasExtra(EnumParcelizeDataParamater.ParcelizeData))
       {
          intent.getParcelableExtra(
               EnumParcelizeDataParamater.ParcelizeData,
               ParcelizeDataParamater::class.java)!!
        }
        else {

            ParcelizeDataParamater()

        }

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            (EnumDeepLink.MyAppDeepLink + parcelizeDataParamater.ma_chucnang).toUri(),
            context,
            MainMenuActivity::class.java
        )

        val requestCode = System.currentTimeMillis().toInt()
        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(requestCode, flag)
        }

        //Tạo chanel thông báo
        val channel = NotificationChannel(EnumChannel.MainChannelId,
            EnumChannel.MainChannelName,
            NotificationManager.IMPORTANCE_DEFAULT)
        channel.description= "Thông báo"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        //Hiện thông báo lên taskbar
        val notification = NotificationCompat.Builder(
            context,
            EnumChannel.MainChannelId)
           .setContentText(parcelizeDataParamater.m_text)
           .setContentTitle(parcelizeDataParamater.m_title)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(clickPendingIntent)
            .build()

        //Tạo ra notify thông báo
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(requestCode,notification)



    }
}