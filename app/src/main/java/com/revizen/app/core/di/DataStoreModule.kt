package com.revizen.app.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.revizen.app.core.datastore.ReviZenDataStore
import com.revizen.app.core.datastore.SettingsPreferences
import com.revizen.app.core.datastore.UserPreferencesRepository
import com.revizen.app.core.datastore.UserPreferencesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "revizen_preferences")

/**
 * Hilt module to configure local DataStore preferences instances and repositories.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.userPreferencesDataStore
    }

    @Provides
    @Singleton
    fun provideReviZenDataStore(
        dataStore: DataStore<Preferences>
    ): ReviZenDataStore {
        return ReviZenDataStore(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        dataStore: ReviZenDataStore
    ): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideSettingsPreferences(
        @ApplicationContext context: Context
    ): SettingsPreferences {
        return SettingsPreferences(context)
    }
}
