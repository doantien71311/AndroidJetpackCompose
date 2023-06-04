package com.example.myapplicationjetpackcompose.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.app.PendingIntent.FLAG_MUTABLE
import android.app.TaskStackBuilder
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplicationjetpackcompose.R
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

object EnumFirebaseMessagingService {

    const val ma_chucnang : String = "ma_chucnang"
    const val key : String = "key"
    const val tungay : String = "tungay"
    const val denngay : String = "denngay"
    const val body : String = "body"
    const val title : String = "title"

}


class CustomFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TAG" , "Refreshed token: $token")
        print("message")
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        //sendRegistrationToServer(token)
    }

    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(ContentValues.TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(ContentValues.TAG, "Message data payload: ${remoteMessage.data}")

//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob()
//            } else {
//                // Handle message within 10 seconds
//                handleNow()
//            }
        }

//        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(ContentValues.TAG, "Message Notification Body: ${it.body}")
//        }


        remoteMessage.data.let {

            showNotificationOnStatusBar(it)

        }

        if (remoteMessage.notification != null) {

            Log.v("CloudMessage", "Notification")

        }



        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]



    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    private fun showNotificationOnStatusBar (data: Map<String, String>) {

        //Create Intent it will be launched when user tap on notification from status bar.
        val intent = Intent(this, MainMenuActivity::class.java).apply {

            flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        }

        intent.putExtra(EnumFirebaseMessagingService.body, data[EnumFirebaseMessagingService.body])
        intent.putExtra(EnumFirebaseMessagingService.title, data[EnumFirebaseMessagingService.title])
        intent.putExtra(EnumFirebaseMessagingService.ma_chucnang, data[EnumFirebaseMessagingService.ma_chucnang])
        intent.putExtra(EnumFirebaseMessagingService.tungay, EnumFirebaseMessagingService.tungay)
        intent.putExtra(EnumFirebaseMessagingService.denngay, EnumFirebaseMessagingService.denngay)

//        //Tiến bổ sung tạo deepLink từ firebase
//        val deepLinkIntent = Intent(Intent.ACTION_VIEW,
//            ("deeplink://"+data[EnumFirebaseMessagingService.ma_chucnang]).toUri(),
//            this,
//            MainMenuActivity::class.java)


        // it should be ungive when push comes.
        var requestCode = System.currentTimeMillis().toInt()
        var pendingIntent: PendingIntent


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

//            TaskStackBuilder.create(this).run {
//
//                addNextIntentWithParentStack(deepLinkIntent).getPendingIntent(
//                    requestCode,
//                    FLAG_MUTABLE
//                )
//
//
//            }


            pendingIntent =
                PendingIntent.getActivity(this, requestCode, intent, FLAG_MUTABLE)

//            pendingIntent =
//               PendingIntent.getActivity(this, requestCode, deepLinkIntent, FLAG_MUTABLE)


        } else {

//            TaskStackBuilder.create(this).run {
//
//                addNextIntentWithParentStack(deepLinkIntent).getPendingIntent(
//                    requestCode,
//                    FLAG_CANCEL_CURRENT
//                )
//
//
//            }

            pendingIntent =
                PendingIntent.getActivity(
                    this,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

//            pendingIntent =
//                PendingIntent.getActivity(
//                    this,
//                    requestCode,
//                    deepLinkIntent,
//                    PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                )


        }

        intent.putExtra(EnumFirebaseMessagingService.body, data[EnumFirebaseMessagingService.body])
        intent.putExtra(EnumFirebaseMessagingService.title, data[EnumFirebaseMessagingService.title])
        intent.putExtra(EnumFirebaseMessagingService.ma_chucnang, data[EnumFirebaseMessagingService.ma_chucnang])
        intent.putExtra(EnumFirebaseMessagingService.tungay, EnumFirebaseMessagingService.tungay)
        intent.putExtra(EnumFirebaseMessagingService.denngay, EnumFirebaseMessagingService.denngay)


        val builder = NotificationCompat.Builder(this, "Global").setAutoCancel(true)

            .setContentTitle(data[EnumFirebaseMessagingService.body])
            .setContentText(data[EnumFirebaseMessagingService.title])
            .setContentTitle(data[EnumFirebaseMessagingService.ma_chucnang])
            .setContentText(data[EnumFirebaseMessagingService.tungay])
            .setContentTitle(data[EnumFirebaseMessagingService.denngay])

            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText((data[EnumFirebaseMessagingService.body])))
            .setContentIntent(pendingIntent)
            //.setSmallIcon(R.drawable.ic_notification)
            .setSmallIcon(R.drawable.ic_launcher_background)


          with (NotificationManagerCompat.from(this)) {


              notify(requestCode, builder.build())

          }

    }





        private fun scheduleJob() {
        // [START dispatch_job]
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        WorkManager.getInstance(this)
            .beginWith(work)
            .enqueue()
        // [END dispatch_job]
    }

    private fun handleNow() {
        Log.d(ContentValues.TAG, "Short lived task is done.")
    }

    internal class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
        override fun doWork(): Result {
            // TODO(developer): add long running task here.
            return Result.success()
        }
    }

}