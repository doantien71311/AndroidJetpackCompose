package com.example.myapplicationjetpackcompose.services

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplicationjetpackcompose.MyApp
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


//class DataStoreServices (context: Context) : IDataStoreServies {
//
//
//}

interface IDataStoreServies {

    suspend fun getToken(): String

    suspend fun getBearToken(): String

    suspend fun saveAuthToken(token: String)

}

class DataStoreServices constructor (

    context: Context,

    ) : IDataStoreServies {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)
    private val dataStore = context.dataStore

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val KEY_AUTH = stringPreferencesKey("KEY_AUTH")
    }


    override suspend fun getToken(): String {

        //val preferences = dataStore.data.first()
        //return preferences[DataStoreServices.KEY_AUTH]!!

        //Tiến thêm vào để test
        return  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiJkODllMjZlYy1iY2E4LTRkY2MtYmU5Mi1lNDkzNmJhZTljYjUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0.7zVtnniMwBgpF-ahU90WcUh6DWpKDl1GWRQ-icGRYFs"

    }

    override suspend fun getBearToken(): String {

        val bearToken: String = "Bearer " + this.getToken()
        return bearToken
    }

    override suspend fun saveAuthToken (authToken: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreServices.KEY_AUTH] = authToken
        }

    }



}