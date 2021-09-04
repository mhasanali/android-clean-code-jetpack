package com.example.doctorconsultationapp.utilities.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "user_pref")
class UserPreferences @Inject constructor(private val context: Context) {

    suspend fun saveUserName(userName: String){
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
            preferences[USER_ID] = "a"
        }
    }
//
//    suspend fun deleteUserPrefs(){
//        context.dataStore.edit {
//            it.clear()
//        }
//    }

    val userName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }

    companion object{
        val USER_NAME = stringPreferencesKey(name = "user_name")
        val USER_ID = stringPreferencesKey(name = "user_id")
    }
}