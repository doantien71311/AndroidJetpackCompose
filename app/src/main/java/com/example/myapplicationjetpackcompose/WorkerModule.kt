package com.example.myapplicationjetpackcompose

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.example.myapplicationjetpackcompose.alarmmanager.AlarmScheduler
import com.example.myapplicationjetpackcompose.alarmmanager.IAlarmScheduler
import com.example.myapplicationjetpackcompose.lookup.chucvu.ILookupChucVuViewModel
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuViewModel

import com.example.myapplicationjetpackcompose.services.DataStoreServices
import com.example.myapplicationjetpackcompose.services.EnumFirebaseMessagingService

import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien.KichHoatThanhVienViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun kichHoatThanhVienViewModelFactory(): KichHoatThanhVienViewModel.Factory
}

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {



    @Singleton
    @Provides
    fun getDataStoreServies(@ApplicationContext app: Context): IDataStoreServies {

        return DataStoreServices(app)

    }

    @Singleton
    @Provides
    fun getAlarmScheduler(@ApplicationContext app: Context): IAlarmScheduler {

        return AlarmScheduler(app)

    }


    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        val intent = Intent(context, MyReceiver::class.java).apply {
            putExtra("MESSAGE", "Clicked!")
        }
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            flag
        )
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
//        return NotificationCompat.Builder(context, "Main Channel ID")
//            .setContentTitle("Welcome")
//            .setContentText("YouTube Channel: Stevdza-San")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
////            .setPublicVersion(
////                NotificationCompat.Builder(context, "Main Channel ID")
////                    .setContentTitle("Hidden")
////                    .setContentText("Unlock to see the message.")
////                    .build()
////            )
//            .addAction(0, "ACTION", pendingIntent)
//            .setContentIntent(clickPendingIntent)


        return NotificationCompat.Builder(context,
           "Main Channel ID"
           //    "ABC ID"
        )
            .setAutoCancel(true)
            .setContentTitle("Welcome")
            .setContentText("YouTube Channel: Stevdza-San")

//            .setContentTitle(data[EnumFirebaseMessagingService.ma_chucnang])
//            .setContentText(data[EnumFirebaseMessagingService.tungay])
//            .setContentTitle(data[EnumFirebaseMessagingService.denngay])

            //.setSmallIcon(R.drawable.ic_notification)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            //.addAction(0, "ACTION", pendingIntent)
            .setContentIntent(clickPendingIntent)






    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }



}