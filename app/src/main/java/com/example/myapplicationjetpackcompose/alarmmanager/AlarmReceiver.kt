package com.example.myapplicationjetpackcompose.alarmmanager

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

//        val message = intent?.getStringExtra("EXTRA_MESSAGE")
//        if (message != null) {
//            Toast.makeText(
//                context,
//                message,
//                Toast.LENGTH_LONG
//            ).show()
//        }

        val notification = Notification.Builder(
            context, "sad"
        )
          //  .setSmallIcon()



    }
}