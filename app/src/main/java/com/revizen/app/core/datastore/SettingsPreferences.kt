package com.revizen.app.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preferences")

/**
 * Standard app settings repository utilizing DataStore Preferences.
 */
@Singleton
class SettingsPreferences @Inject constructor(
    private val context: Context
) {
    private val dataStore = context.settingsDataStore

    companion object {
        private val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    }

    val isDarkModeEnabled: Flow<Boolean?> = dataStore.data.map { preferences ->
        preferences[DARK_MODE_ENABLED]
    }

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_ENABLED] = enabled
        }
    }
}
