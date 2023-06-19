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

interface IDataStoreServies {

    suspend fun getToken():  String
    suspend fun getBearToken(): String
    suspend fun saveKeyToken(token: String)



    suspend fun getUserName():  String
    suspend fun saveUserName(ma_nsd: String)


    suspend fun getMaNV():  String
    suspend fun saveMaNV(ma_nv: String)



    suspend fun getPassWord():  String

    suspend fun savePassWord(matkhau: String)



    suspend fun clearDataStore()



    suspend fun getDTOParamater(): dto_paramater

}

class DataStoreServices constructor (

    context: Context,

    ) : IDataStoreServies {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)
    private val dataStore = context.dataStore

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val KEY_AUTH = stringPreferencesKey("KEY_AUTH")
        private val KEY_TOKEN = stringPreferencesKey("KEY_TOKEN")
        private val KEY_USERNAME = stringPreferencesKey("KEY_USERNAME")
        private val KEY_PASSWORD = stringPreferencesKey("KEY_PASSWORD")
        private val KEY_MANV = stringPreferencesKey("KEY_MANV")

    }

//    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
//        preferences[DataStoreServices.KEY_TOKEN] ?: ""
//    }

    override suspend fun getToken(): String {

//        val preferences = dataStore.data.first()
//        val sad =  preferences[DataStoreServices.KEY_TOKEN]
//        return preferences[DataStoreServices.KEY_TOKEN] ?: ""


        //Tiến thêm vào để test
        //return  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiJkODllMjZlYy1iY2E4LTRkY2MtYmU5Mi1lNDkzNmJhZTljYjUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0.7zVtnniMwBgpF-ahU90WcUh6DWpKDl1GWRQ-icGRYFs"

        return dataStore.data
            .map { preference ->
                preference[DataStoreServices.KEY_TOKEN] ?: ""
            }.first()
    }

    override suspend fun getUserName(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreServices.KEY_USERNAME] ?: ""
            }.first()
    }

    override suspend fun getMaNV(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreServices.KEY_MANV] ?: ""
            }.first()
    }

    override suspend fun getPassWord(): String {
        return dataStore.data
            .map { preference ->
                preference[DataStoreServices.KEY_PASSWORD] ?: ""
            }.first()
    }


    override suspend fun clearDataStore() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun getBearToken(): String {

        return "Bearer " + getToken()
    }

    override suspend fun saveKeyToken(authToken: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreServices.KEY_TOKEN] = authToken
        }

    }

    override suspend fun saveUserName(ma_nsd: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreServices.KEY_USERNAME] = ma_nsd
        }

    }

    override suspend fun savePassWord(matkhau: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreServices.KEY_PASSWORD] = matkhau
        }
    }

    override suspend fun saveMaNV(ma_nv: String) {

        dataStore.edit { preferences ->
            preferences[DataStoreServices.KEY_MANV] = ma_nv
        }
    }

    override suspend fun getDTOParamater(): dto_paramater {

        return dto_paramater(
            getMaNV(),
            getUserName(),
            getUserName(),
        )

    }
}