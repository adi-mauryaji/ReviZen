package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.StudySession
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [StudySession] operations.
 * Provides queries for study analytics and total study time tracking.
 */
@Dao
interface StudySessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: StudySession): Long

    @Update
    suspend fun update(session: StudySession)

    @Delete
    suspend fun delete(session: StudySession)

    @Query("SELECT * FROM study_sessions ORDER BY start_time DESC")
    fun getAll(): Flow<List<StudySession>>

    @Query("SELECT * FROM study_sessions WHERE start_time BETWEEN :start AND :end ORDER BY start_time DESC")
    fun getInRange(start: Long, end: Long): Flow<List<StudySession>>

    @Query("SELECT COALESCE(SUM(duration_minutes), 0) FROM study_sessions")
    fun getTotalStudyTime(): Flow<Long>
}
