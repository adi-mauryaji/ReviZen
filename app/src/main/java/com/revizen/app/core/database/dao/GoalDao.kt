package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.Goal
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [Goal] operations.
 */
@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: Goal): Long

    @Update
    suspend fun update(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)

    @Query("SELECT * FROM goals ORDER BY created_at DESC")
    fun getAll(): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE is_completed = 0 ORDER BY target_date ASC")
    fun getActive(): Flow<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :id")
    fun getById(id: Long): Flow<Goal?>
}
