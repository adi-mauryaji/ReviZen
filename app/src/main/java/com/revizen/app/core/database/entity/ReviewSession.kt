package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Records a single review event for a [MemoryCard].
 * Tracks the user's rating, response time, and resulting SM-2 parameter changes.
 */
@Entity(
    tableName = "review_sessions",
    foreignKeys = [
        ForeignKey(
            entity = MemoryCard::class,
            parentColumns = ["id"],
            childColumns = ["card_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["card_id"])]
)
data class ReviewSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "card_id")
    val cardId: Long,

    @ColumnInfo(name = "session_date")
    val sessionDate: Long = System.currentTimeMillis(),

    /** User self-assessed quality rating: 0 (blackout) to 5 (perfect) */
    val rating: Int,

    @ColumnInfo(name = "response_time_ms")
    val responseTimeMs: Long = 0L,

    @ColumnInfo(name = "previous_interval")
    val previousInterval: Int = 0,

    @ColumnInfo(name = "new_interval")
    val newInterval: Int = 0,

    @ColumnInfo(name = "previous_ease")
    val previousEase: Float = 2.5f,

    @ColumnInfo(name = "new_ease")
    val newEase: Float = 2.5f,

    @ColumnInfo(name = "was_correct")
    val wasCorrect: Boolean = true
)
