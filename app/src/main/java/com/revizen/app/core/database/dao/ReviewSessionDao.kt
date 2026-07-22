package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.ReviewSession
import com.revizen.app.core.database.model.AccuracyStats
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [ReviewSession] operations.
 * Provides queries for review history analysis and accuracy statistics.
 */
@Dao
interface ReviewSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: ReviewSession): Long

    @Update
    suspend fun update(session: ReviewSession)

    @Delete
    suspend fun delete(session: ReviewSession)

    @Query("SELECT * FROM review_sessions WHERE card_id = :cardId ORDER BY session_date DESC")
    fun getSessionsForCard(cardId: Long): Flow<List<ReviewSession>>

    @Query("SELECT * FROM review_sessions WHERE session_date BETWEEN :start AND :end ORDER BY session_date DESC")
    fun getSessionsInRange(start: Long, end: Long): Flow<List<ReviewSession>>

    @Query("SELECT COUNT(*) as totalReviews, SUM(CASE WHEN was_correct = 1 THEN 1 ELSE 0 END) as correctCount FROM review_sessions")
    fun getAccuracyStats(): Flow<AccuracyStats>
}
