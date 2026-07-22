package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revizen.app.core.database.entity.AppSettings
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the single-row [AppSettings] entity.
 * Uses upsert semantics to ensure only one settings row exists.
 */
@Dao
interface AppSettingsDao {

    @Query("SELECT * FROM app_settings WHERE id = 1")
    fun getSettings(): Flow<AppSettings?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSettings(settings: AppSettings)
}
