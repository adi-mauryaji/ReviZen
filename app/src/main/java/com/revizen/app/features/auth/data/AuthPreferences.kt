package com.revizen.app.features.auth.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")

/**
 * DataStore preferences manager for Auth settings.
 */
@Singleton
class AuthPreferences @Inject constructor(
    private val context: Context
) {
    private val dataStore = context.authDataStore

    companion object {
        private val IS_PIN_SET = booleanPreferencesKey("is_pin_set")
        private val IS_BIOMETRICS_ENABLED = booleanPreferencesKey("is_biometrics_enabled")
        private val LAST_ACTIVE_TIMESTAMP = longPreferencesKey("last_active_timestamp")
    }

    val isPinSet: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_PIN_SET] ?: false
    }

    val isBiometricsEnabled: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_BIOMETRICS_ENABLED] ?: false
    }

    val lastActiveTimestamp: Flow<Long> = dataStore.data.map { preferences ->
        preferences[LAST_ACTIVE_TIMESTAMP] ?: 0L
    }

    suspend fun setPinSet(isSet: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_PIN_SET] = isSet
        }
    }

    suspend fun setBiometricsEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_BIOMETRICS_ENABLED] = enabled
        }
    }

    suspend fun updateLastActiveTimestamp(timestamp: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_ACTIVE_TIMESTAMP] = timestamp
        }
    }

    suspend fun clearAuthPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
