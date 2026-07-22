package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A learning goal with milestone tracking and progress measurement.
 * Milestones are stored as a JSON-encoded list of strings.
 */
@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val description: String = "",

    @ColumnInfo(name = "target_date")
    val targetDate: Long = 0L,

    val category: String = "",

    /** JSON-encoded list of milestone strings */
    val milestones: String = "[]",

    /** Progress percentage: 0.0 to 1.0 */
    val progress: Float = 0f,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
