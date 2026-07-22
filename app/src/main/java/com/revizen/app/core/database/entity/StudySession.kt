package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Records a timed study session with performance metrics.
 * Used by the analytics module to visualize study patterns and productivity.
 */
@Entity(tableName = "study_sessions")
data class StudySession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "start_time")
    val startTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    val endTime: Long = 0L,

    @ColumnInfo(name = "duration_minutes")
    val durationMinutes: Int = 0,

    val topic: String = "",

    @ColumnInfo(name = "cards_reviewed")
    val cardsReviewed: Int = 0,

    @ColumnInfo(name = "correct_answers")
    val correctAnswers: Int = 0,

    /** Self-assessed focus quality: 1-10 */
    @ColumnInfo(name = "focus_score")
    val focusScore: Int = 5,

    /** Mood scale: 1 (terrible) to 5 (great) */
    val mood: Int = 3,

    val notes: String = "",

    /** e.g., "review", "learn", "cramming" */
    @ColumnInfo(name = "session_type")
    val sessionType: String = "review"
)
