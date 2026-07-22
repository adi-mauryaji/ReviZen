package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.HabitLog
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [HabitLog] operations.
 * Supports checking daily completion status and date range queries.
 */
@Dao
interface HabitLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: HabitLog): Long

    @Update
    suspend fun update(log: HabitLog)

    @Delete
    suspend fun delete(log: HabitLog)

    @Query("SELECT * FROM habit_logs WHERE habit_id = :habitId ORDER BY completed_at DESC")
    fun getLogsForHabit(habitId: Long): Flow<List<HabitLog>>

    @Query("SELECT * FROM habit_logs WHERE completed_at BETWEEN :start AND :end ORDER BY completed_at DESC")
    fun getLogsInRange(start: Long, end: Long): Flow<List<HabitLog>>

    @Query("SELECT EXISTS(SELECT 1 FROM habit_logs WHERE habit_id = :habitId AND completed_at BETWEEN :dayStart AND :dayEnd LIMIT 1)")
    fun isCompletedToday(habitId: Long, dayStart: Long, dayEnd: Long): Flow<Boolean>
}
