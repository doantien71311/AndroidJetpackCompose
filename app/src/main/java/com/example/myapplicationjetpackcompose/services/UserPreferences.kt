package com.example.myapplicationjetpackcompose.services

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class UserPreferences (context: Context)
{

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = UserPreferences.USER_PREFERENCES_NAME)
    private val dataStore = context.dataStore

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val KEY_AUTH = stringPreferencesKey("KEY_AUTH")
        private const val EXAMPLE_COUNTER = "EXAMPLE_COUNTER"
    }

   private val applicationContext = context.applicationContext

//    private val dataStore: DataStore<Preferences>
//    init {
//
//        dataStore = applicationContext.createDataStore("dasdsad")
//
//    }


    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->

            preferences[KEY_AUTH]

        }


    suspend fun saveAuthToken (authToken: String) {

        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken


        }

    }


}



