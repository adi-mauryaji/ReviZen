package com.revizen.app.core.datastore

/**
 * Model class containing all user preference options and app telemetry metrics.
 */
data class UserPreferences(
    val onboardingCompleted: Boolean = false,
    val themePreference: String = "system",
    val dailyReviewGoal: Int = 20,
    val reviewNotificationEnabled: Boolean = true,
    val reviewNotificationHour: Int = 9,
    val reviewNotificationMinute: Int = 0,
    val breakReminderEnabled: Boolean = true,
    val breakIntervalMinutes: Int = 25,
    val defaultCategory: String = "General",
    val lastReviewDate: Long = 0L,
    val streakCount: Int = 0,
    val longestStreak: Int = 0,
    val totalCardsReviewed: Int = 0,
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true,
    val fontSizePreference: String = "medium",
    val focusModeActive: Boolean = false,
    val currentUserName: String = "User",
    val companionLastDismissed: Long = 0L
)
