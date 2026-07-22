package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user-defined habit to track.
 * Supports daily/weekly/custom frequency with streak tracking.
 */
@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,
    val description: String = "",

    /** Material icon name reference (e.g., "fitness_center") */
    @ColumnInfo(name = "icon_name")
    val iconName: String = "check_circle",

    /** Hex color string for UI display (e.g., "#6C63FF") */
    @ColumnInfo(name = "color_hex")
    val colorHex: String = "#6C63FF",

    /** "daily", "weekly", or "custom" */
    val frequency: String = "daily",

    /** JSON-encoded list of day indices for custom frequency (e.g., [1,3,5]) */
    @ColumnInfo(name = "custom_days")
    val customDays: String = "[]",

    /** HH:mm formatted reminder time */
    @ColumnInfo(name = "reminder_time")
    val reminderTime: String = "",

    val streak: Int = 0,

    @ColumnInfo(name = "longest_streak")
    val longestStreak: Int = 0,

    @ColumnInfo(name = "total_completions")
    val totalCompletions: Int = 0,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "is_active")
    val isActive: Boolean = true,

    val category: String = ""
)
