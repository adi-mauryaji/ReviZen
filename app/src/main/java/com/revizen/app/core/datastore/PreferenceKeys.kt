package com.revizen.app.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Registry of Preference Keys used to read and write app settings and metrics in DataStore.
 */
object PreferenceKeys {
    val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    val THEME_PREFERENCE = stringPreferencesKey("theme_preference")
    val DAILY_REVIEW_GOAL = intPreferencesKey("daily_review_goal")
    val REVIEW_NOTIFICATION_ENABLED = booleanPreferencesKey("review_notification_enabled")
    val REVIEW_NOTIFICATION_HOUR = intPreferencesKey("review_notification_hour")
    val REVIEW_NOTIFICATION_MINUTE = intPreferencesKey("review_notification_minute")
    val BREAK_REMINDER_ENABLED = booleanPreferencesKey("break_reminder_enabled")
    val BREAK_INTERVAL_MINUTES = intPreferencesKey("break_interval_minutes")
    val DEFAULT_CATEGORY = stringPreferencesKey("default_category")
    val LAST_REVIEW_DATE = longPreferencesKey("last_review_date")
    val STREAK_COUNT = intPreferencesKey("streak_count")
    val LONGEST_STREAK = intPreferencesKey("longest_streak")
    val TOTAL_CARDS_REVIEWED = intPreferencesKey("total_cards_reviewed")
    val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
    val HAPTIC_ENABLED = booleanPreferencesKey("haptic_enabled")
    val FONT_SIZE_PREFERENCE = stringPreferencesKey("font_size_preference")
    val FOCUS_MODE_ACTIVE = booleanPreferencesKey("focus_mode_active")
    val CURRENT_USER_NAME = stringPreferencesKey("current_user_name")
    val COMPANION_LAST_DISMISSED = longPreferencesKey("companion_last_dismissed")
}
