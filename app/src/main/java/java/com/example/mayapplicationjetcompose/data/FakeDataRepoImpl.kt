package java.com.example.mayapplicationjetcompose.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplicationjetpackcompose.model.dto_paramater
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FakeDataRepoImpl @Inject constructor (

    context: Context,

    ) : IDataStoreServies
{

    //private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)
   // private val dataStore = context.dataStore

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
        return  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJTcWxfU2VydmVyX05hbWUiOiI0MUI4RDNEQjAzRkEyMEI3QUQxQTBEMzBDQzcyNkZBODA1MzM0ODYwQkI5OEJDMTUiLCJTcWxfRGF0YWJhc2VfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1VzZXJfTmFtZSI6IkNEMUNENjU0QkZEQTRENjczMTJFOEJFMDc4N0VDOTlBIiwiU3FsX1Bhc3N3b3JkIjoiMjcyNzREMjNDRDc3NDdFMTNENDg4QkM1NERGNEJBQTQ4MDJENThBNDkyQTFDNzFBIiwibV9Vc2VySWQiOiJCOEJBQzZCMDQ5NzA5QjEzNEFCNEI1NEY1N0Q5RTFDMERCMDY4RTkxMDkxQjEwNDciLCJqdGkiOiJkODllMjZlYy1iY2E4LTRkY2MtYmU5Mi1lNDkzNmJhZTljYjUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjU5OTIxIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo0MjAwIn0.7zVtnniMwBgpF-ahU90WcUh6DWpKDl1GWRQ-icGRYFs"

//        return dataStore.data
//            .map { preference ->
//                preference[FakeDataRepoImpl.KEY_TOKEN] ?: ""
//            }.first()
    }

    override suspend fun getUserName(): String {
//        return dataStore.data
//            .map { preference ->
//                preference[FakeDataRepoImpl.KEY_USERNAME] ?: ""
//            }.first()

        return "ADMIN"
    }

    override suspend fun getMaNV(): String {
//        return dataStore.data
//            .map { preference ->
//                preference[FakeDataRepoImpl.KEY_MANV] ?: ""
//            }.first()

        return "ADMIN"
    }

    override suspend fun getPassWord(): String {
//        return dataStore.data
//            .map { preference ->
//                preference[FakeDataRepoImpl.KEY_PASSWORD] ?: ""
//            }.first()


        return  "123"
    }


    override suspend fun clearDataStore() {
//        dataStore.edit { preferences ->
//            preferences.clear()
//        }
    }

    override suspend fun getBearToken(): String {

        return "Bearer " + getToken()
    }

    override suspend fun saveKeyToken(authToken: String) {

//        dataStore.edit { preferences ->
//            preferences[FakeDataRepoImpl.KEY_TOKEN] = authToken
//        }

    }

    override suspend fun saveUserName(ma_nsd: String) {
//
//        dataStore.edit { preferences ->
//            preferences[FakeDataRepoImpl.KEY_USERNAME] = ma_nsd
//        }

    }

    override suspend fun savePassWord(matkhau: String) {
//
//        dataStore.edit { preferences ->
//            preferences[FakeDataRepoImpl.KEY_PASSWORD] = matkhau
//        }
    }

    override suspend fun saveMaNV(ma_nv: String) {

//        dataStore.edit { preferences ->
//            preferences[FakeDataRepoImpl.KEY_MANV] = ma_nv
//        }
    }

    override suspend fun getDTOParamater(): dto_paramater {

        return dto_paramater(
            getMaNV(),
            getUserName(),
            getUserName(),
        )

    }
}
