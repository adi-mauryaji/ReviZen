package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Single-row settings entity (id always = 1).
 * Stores all user-configurable app preferences that affect
 * the spaced repetition algorithm, notifications, and theming.
 */
@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey
    val id: Int = 1,

    /** "light", "dark", or "system" */
    val theme: String = "system",

    @ColumnInfo(name = "daily_goal_cards")
    val dailyGoalCards: Int = 20,

    @ColumnInfo(name = "review_notifications_enabled")
    val reviewNotificationsEnabled: Boolean = true,

    /** HH:mm formatted time string */
    @ColumnInfo(name = "review_notification_time")
    val reviewNotificationTime: String = "09:00",

    @ColumnInfo(name = "break_reminder_enabled")
    val breakReminderEnabled: Boolean = true,

    @ColumnInfo(name = "break_interval_minutes")
    val breakIntervalMinutes: Int = 25,

    @ColumnInfo(name = "default_card_category")
    val defaultCardCategory: String = "General",

    /** SM-2 ease penalty applied on incorrect answers */
    @ColumnInfo(name = "sm2_ease_penalty")
    val sm2EasePenalty: Float = 0.2f,

    /** SM-2 ease bonus applied on perfect answers */
    @ColumnInfo(name = "sm2_ease_bonus")
    val sm2EaseBonus: Float = 0.15f
)
