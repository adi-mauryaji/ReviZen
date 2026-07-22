package com.revizen.app.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object managing app configurations.
 */
@Dao
interface UserConfigDao {

    @Query("SELECT * FROM user_config WHERE id = 'config'")
    fun getUserConfig(): Flow<UserConfigEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserConfig(config: UserConfigEntity)
}
