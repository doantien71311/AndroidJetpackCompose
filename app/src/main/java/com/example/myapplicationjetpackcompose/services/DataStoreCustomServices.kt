package com.example.myapplicationjetpackcompose.services

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplicationjetpackcompose.model.dto_paramater
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


//class DataStoreServices (context: Context) : IDataStoreServies {
//
//
//}

class DataStoreCustomServices constructor (

    context: Context,

    ) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME_CUS)
    private val dataStore = context.dataStore

    companion object {
        private const val USER_PREFERENCES_NAME_CUS = "user_preferences_cus"
        private val KEY_AUTH_CUS = stringPreferencesKey("KEY_AUTH_CUS")
        private val KEY_TOKEN_CUS = stringPreferencesKey("KEY_TOKEN_CUS")
        private val KEY_USERNAME_CUS = stringPreferencesKey("KEY_USERNAME_CUS")
        private val KEY_PASSWORD_CUS = stringPreferencesKey("KEY_PASSWORD_CUS")
        private val KEY_MANV_CUS = stringPreferencesKey("KEY_MANV_CUS")

    }

//    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
//        preferences[DataStoreServices.KEY_TOKEN] ?: ""
//    }

    suspend fun getToken(): String {

//        val preferences = dataStore.data.first()
//        val sad =  preferences[DataStoreServices.KEY_TOKEN]
//        return preferences[DataStoreServices.KEY_TOKEN] ?: ""


        //Tiến thêm vào để test
        //return  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiJkODllMjZlYy1iY2E4LTRkY2MtYmU5Mi1lNDkzNmJhZTljYjUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0.7zVtnniMwBgpF-ahU90WcUh6DWpKDl1GWRQ-icGRYFs"

        return dataStore.data
            .map { preference ->
                preference[DataStoreCustomServices.KEY_TOKEN_CUS] ?: ""
            }.first()
    }

    suspend fun getUserName(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreCustomServices.KEY_USERNAME_CUS] ?: ""
            }.first()
    }

    suspend fun getMaNV(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreCustomServices.KEY_MANV_CUS] ?: ""
            }.first()
    }

     suspend fun getPassWord(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreCustomServices.KEY_PASSWORD_CUS] ?: ""
            }.first()
    }


     suspend fun clearDataStore() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

     suspend fun getBearToken(): String {

        return "Bearer " + getToken()
    }

     suspend fun saveKeyToken(authToken: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreCustomServices.KEY_TOKEN_CUS] = authToken
        }

    }

     suspend fun saveUserName(ma_nsd: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreCustomServices.KEY_USERNAME_CUS] = ma_nsd
        }

    }

     suspend fun savePassWord(matkhau: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreCustomServices.KEY_PASSWORD_CUS] = matkhau
        }
    }

     suspend fun saveMaNV(ma_nv: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreCustomServices.KEY_MANV_CUS] = ma_nv
        }
    }

     suspend fun getDTOParamater(): dto_paramater {

        return dto_paramater(
            getMaNV(),
            getUserName(),
            getUserName(),
        )

    }
}