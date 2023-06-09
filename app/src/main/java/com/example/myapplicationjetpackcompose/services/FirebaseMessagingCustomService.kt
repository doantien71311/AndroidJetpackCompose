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
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplicationjetpackcompose.MainActivity
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



class CustomFirebaseMessagingService (): FirebaseMessagingService() {



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

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
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

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0

        val urlLink = ("myapp://details/").toUri()
        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            //"$MY_URI/$MY_ARG".toUri(),
            "myapp://details/".toUri(),
            this,
            MainActivity::class.java
        )


        // it should be ungive when push comes.
        val requestCode = System.currentTimeMillis().toInt()

//        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(clickIntent)
//            getPendingIntent(1, flag)
//        }

        val clickPendingIntent =
            PendingIntent.getActivity(this, 0, clickIntent, flag)

        val builder = NotificationCompat.Builder(this,
            "Global"
           //"default_notification_channel_id"
            //"Main Channel ID"

        )
            .setAutoCancel(true)
            .setContentTitle("Welcome")
            .setContentText("YouTube Channel: Stevdza-San")
            //.setContentTitle(data[EnumFirebaseMessagingService.body])
            //.setContentText(data[EnumFirebaseMessagingService.title])
//            .setContentTitle(data[EnumFirebaseMessagingService.ma_chucnang])
//            .setContentText(data[EnumFirebaseMessagingService.tungay])
//            .setContentTitle(data[EnumFirebaseMessagingService.denngay])

            //.setSmallIcon(R.drawable.ic_notification)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //.setStyle(NotificationCompat.BigTextStyle().bigText((data[EnumFirebaseMessagingService.body])))
            .setContentIntent(clickPendingIntent)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }


        try {
            // This is only necessary for API level >= 33 (TIRAMISU)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
//                    // FCM SDK (and your app) can post notifications.
//                    with(NotificationManagerCompat.from(applicationContext)) {
//                        notify(1, builder.build())
//                    }

                    //notificationManager.notify(1,builder.build())


                }
                else {

                    Log.w("TAG", "Không có quyền POST_NOTIFICATIONS")

                }
            }
        }
        catch (e: Exception){

            Log.w("TAG", "Lỗi: " +e.toString())

        }

    }


    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    private fun showNotificationOnStatusBar2 (data: Map<String, String>) {

//        //Create Intent it will be launched when user tap on notification from status bar.
//        val intent = Intent(this, MainMenuActivity::class.java).apply {
//
//            flags =
//                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//
//        }

//        //Tiến bổ sung tạo deepLink từ firebase
//        val deepLinkIntent = Intent(Intent.ACTION_VIEW,
//            ("deeplink://"+data[EnumFirebaseMessagingService.ma_chucnang]).toUri(),
//            this,
//            MainMenuActivity::class.java)

        val url_link = ("myapp://details").toUri()

        Log.w("TAG", "url_link: " +url_link.toString())

        val deepLinkIntent = Intent(
            Intent.ACTION_VIEW,
            url_link,
            this,
            MainMenuActivity::class.java).apply {

            flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        }

        // it should be ungive when push comes.
        var requestCode = System.currentTimeMillis().toInt()
        var pendingIntent: PendingIntent
        =

        //pendingIntent =
            //PendingIntent.getActivity(this, requestCode, deepLinkIntent, FLAG_MUTABLE)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            TaskStackBuilder.create(this).run {

                addNextIntentWithParentStack(deepLinkIntent).getPendingIntent(
                    requestCode,
                    FLAG_MUTABLE
                )


            }


//            pendingIntent =
//               PendingIntent.getActivity(this, requestCode, deepLinkIntent, FLAG_MUTABLE)



        } else {

            TaskStackBuilder.create(this).run {

                addNextIntentWithParentStack(deepLinkIntent).getPendingIntent(
                    requestCode,
                    FLAG_CANCEL_CURRENT
                )


            }
//
//            pendingIntent =
//                PendingIntent.getActivity(
//                    this,
//                    requestCode,
//                    deepLinkIntent,
//                    PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                )

        }


//        deepLinkIntent.putExtra(EnumFirebaseMessagingService.body, data[EnumFirebaseMessagingService.body])
//        deepLinkIntent.putExtra(EnumFirebaseMessagingService.title, data[EnumFirebaseMessagingService.title])
//        deepLinkIntent.putExtra(EnumFirebaseMessagingService.ma_chucnang, data[EnumFirebaseMessagingService.ma_chucnang])
//        deepLinkIntent.putExtra(EnumFirebaseMessagingService.tungay, EnumFirebaseMessagingService.tungay)
//        deepLinkIntent.putExtra(EnumFirebaseMessagingService.denngay, EnumFirebaseMessagingService.denngay)

//        intent.putExtra(EnumFirebaseMessagingService.body, data[EnumFirebaseMessagingService.body])
//        intent.putExtra(EnumFirebaseMessagingService.title, data[EnumFirebaseMessagingService.title])
//        intent.putExtra(EnumFirebaseMessagingService.ma_chucnang, data[EnumFirebaseMessagingService.ma_chucnang])
//        intent.putExtra(EnumFirebaseMessagingService.tungay, EnumFirebaseMessagingService.tungay)
//        intent.putExtra(EnumFirebaseMessagingService.denngay, EnumFirebaseMessagingService.denngay)


        val builder = NotificationCompat.Builder(this, "Global").setAutoCancel(true)

            .setContentTitle(data[EnumFirebaseMessagingService.body])
            .setContentText(data[EnumFirebaseMessagingService.title])
//            .setContentTitle(data[EnumFirebaseMessagingService.ma_chucnang])
//            .setContentText(data[EnumFirebaseMessagingService.tungay])
//            .setContentTitle(data[EnumFirebaseMessagingService.denngay])

            //.setSmallIcon(R.drawable.ic_notification)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setStyle(NotificationCompat.BigTextStyle().bigText((data[EnumFirebaseMessagingService.body])))
            .addAction(0, "ACTION", pendingIntent)
            .setContentIntent(pendingIntent)




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