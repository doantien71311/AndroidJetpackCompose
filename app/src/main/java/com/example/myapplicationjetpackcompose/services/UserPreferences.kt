package com.example.myapplicationjetpackcompose.services

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences

//import java.util.prefs.Preferences

// At the top level of your kotlin file:
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

//private operator fun <T, V> ReadOnlyProperty<T, V>.getValue(userPreferences: UserPreferences, property: KProperty<V?>): DataStore<Preferences> {
//
//}


class UserPreferences (

    context: Context

        )
{

   private val applicationContext = context.applicationContext

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
    }


}

