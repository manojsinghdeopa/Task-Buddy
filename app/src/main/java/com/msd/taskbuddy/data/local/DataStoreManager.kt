package com.msd.taskbuddy.data.local
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("taskbuddy_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val KEY_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    }

    // Default: first launch = true
    val isFirstLaunch: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_FIRST_LAUNCH] ?: true
    }

    suspend fun setFirstLaunchDone() {
        context.dataStore.edit { prefs ->
            prefs[KEY_FIRST_LAUNCH] = false
        }
    }

    // Save login state
    suspend fun saveLoginState(isLoggedIn: Boolean, email: String?) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LOGGED_IN] = isLoggedIn
            if (email != null) prefs[KEY_USER_EMAIL] = email
        }
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_LOGGED_IN] ?: false
    }

    val userEmail: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_USER_EMAIL]
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_LOGGED_IN)
            prefs.remove(KEY_USER_EMAIL)
        }
    }
}

