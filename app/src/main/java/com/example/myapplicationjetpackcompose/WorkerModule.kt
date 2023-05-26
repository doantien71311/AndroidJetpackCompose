package com.example.myapplicationjetpackcompose

import android.content.Context
import com.example.myapplicationjetpackcompose.services.DataStoreServices
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Singleton
    @Provides
    fun getDataStoreServies(@ApplicationContext app: Context): IDataStoreServies {

        return DataStoreServices(app)

    }

//    @Singleton
//    @Provides
//    fun provideApplication(@ApplicationContext app: Context):   MyApp {
//        return app as MyApp
//    }

}