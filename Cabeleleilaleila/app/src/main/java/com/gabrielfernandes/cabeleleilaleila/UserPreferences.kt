package com.gabrielfernandes.cabeleleilaleila

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.gabrielfernandes.cabeleleilaleila.models.User
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val EMAIL_KEY = stringPreferencesKey("user_email")
        private val PASSWORD_KEY = stringPreferencesKey("user_password")
        private val USERNAME_KEY = stringPreferencesKey("user_username")
        private val LOGGED_IN_KEY = booleanPreferencesKey("user_logged")
    }

    suspend fun saveUser(username: String, email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
            preferences[USERNAME_KEY] = username
            preferences[LOGGED_IN_KEY] = true
        }
    }

    val userFlow = context.dataStore.data.map { preferences ->
        User(
            email = preferences[EMAIL_KEY] ?: "",
            password = preferences[PASSWORD_KEY] ?: "",
            username = preferences[USERNAME_KEY] ?: ""
        )
    }

    suspend fun clearUser() {
        context.dataStore.edit { it.clear() }
    }
}
