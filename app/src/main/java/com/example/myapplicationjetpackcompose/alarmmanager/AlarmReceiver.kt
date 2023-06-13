package com.example.myapplicationjetpackcompose.alarmmanager

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.myapplicationjetpackcompose.MainActivity
import com.example.myapplicationjetpackcompose.R
import okhttp3.internal.notify

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

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

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            //"$MY_URI/$MY_ARG".toUri(),
            "myapp://details/".toUri(),
            context,
            MainActivity::class.java
        )

        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, flag)
        }

        val notification = NotificationCompat.Builder(
            context,
            "Main Channel ID"
        )
           .setContentText("asdas432453dsad")
           .setContentTitle("asd4234s")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(clickPendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,notification)



    }
}