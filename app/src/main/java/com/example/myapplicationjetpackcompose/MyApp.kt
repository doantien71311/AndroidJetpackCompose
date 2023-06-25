package com.example.myapplicationjetpackcompose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //Tạo chanel thông báo và hẹn giờ
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                EnumChannel.MainChannelId,
                EnumChannel.MainChannelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Thông báo và hẹn giờ"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

        //Tạo chanel firebase
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelFireBase = NotificationChannel(
                EnumChannel.FirebaseChannelId,
                EnumChannel.FirebaseChannelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channelFireBase.description = "Fire base thông báo"

            val notificationManagerFireBase =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManagerFireBase.createNotificationChannel(channelFireBase)

        }

        // Get token
        // [START log_reg_token]
        Firebase.messaging.getToken().addOnCompleteListener{ task ->
            if (task.isSuccessful) {

                // Get new FCM registraddtion token
                val token = task.result
                Log.w("TAG", "ToKenPCM: " + token)




            }
            else {

                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
            }


        }

        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Installations", "Installation ID: " + task.result)
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }

    }

}