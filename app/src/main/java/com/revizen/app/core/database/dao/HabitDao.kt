package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.Habit
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [Habit] operations.
 */
@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit): Long

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits ORDER BY created_at DESC")
    fun getAll(): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE is_active = 1 ORDER BY created_at DESC")
    fun getActiveHabits(): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :id")
    fun getById(id: Long): Flow<Habit?>
}
