package com.revizen.app.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataStore preferences manager providing a flow of [UserPreferences] and update functions
 * for all keys defined in [PreferenceKeys].
 */
@Singleton
class ReviZenDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val userPreferences: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                onboardingCompleted = preferences[PreferenceKeys.ONBOARDING_COMPLETED] ?: false,
                themePreference = preferences[PreferenceKeys.THEME_PREFERENCE] ?: "system",
                dailyReviewGoal = preferences[PreferenceKeys.DAILY_REVIEW_GOAL] ?: 20,
                reviewNotificationEnabled = preferences[PreferenceKeys.REVIEW_NOTIFICATION_ENABLED] ?: true,
                reviewNotificationHour = preferences[PreferenceKeys.REVIEW_NOTIFICATION_HOUR] ?: 9,
                reviewNotificationMinute = preferences[PreferenceKeys.REVIEW_NOTIFICATION_MINUTE] ?: 0,
                breakReminderEnabled = preferences[PreferenceKeys.BREAK_REMINDER_ENABLED] ?: true,
                breakIntervalMinutes = preferences[PreferenceKeys.BREAK_INTERVAL_MINUTES] ?: 25,
                defaultCategory = preferences[PreferenceKeys.DEFAULT_CATEGORY] ?: "General",
                lastReviewDate = preferences[PreferenceKeys.LAST_REVIEW_DATE] ?: 0L,
                streakCount = preferences[PreferenceKeys.STREAK_COUNT] ?: 0,
                longestStreak = preferences[PreferenceKeys.LONGEST_STREAK] ?: 0,
                totalCardsReviewed = preferences[PreferenceKeys.TOTAL_CARDS_REVIEWED] ?: 0,
                soundEnabled = preferences[PreferenceKeys.SOUND_ENABLED] ?: true,
                hapticEnabled = preferences[PreferenceKeys.HAPTIC_ENABLED] ?: true,
                fontSizePreference = preferences[PreferenceKeys.FONT_SIZE_PREFERENCE] ?: "medium",
                focusModeActive = preferences[PreferenceKeys.FOCUS_MODE_ACTIVE] ?: false,
                currentUserName = preferences[PreferenceKeys.CURRENT_USER_NAME] ?: "User",
                companionLastDismissed = preferences[PreferenceKeys.COMPANION_LAST_DISMISSED] ?: 0L
            )
        }

    // --- Helper Update Functions ---

    suspend fun updateTheme(theme: String) {
        setThemePreference(theme)
    }

    suspend fun completeOnboarding() {
        setOnboardingCompleted(true)
    }

    suspend fun updateDailyGoal(goal: Int) {
        setDailyReviewGoal(goal)
    }

    suspend fun updateNotificationSettings(enabled: Boolean, hour: Int, minute: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_ENABLED] = enabled
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_HOUR] = hour
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_MINUTE] = minute
        }
    }

    suspend fun incrementStreak() {
        dataStore.edit { preferences ->
            val currentStreak = preferences[PreferenceKeys.STREAK_COUNT] ?: 0
            val newStreak = currentStreak + 1
            preferences[PreferenceKeys.STREAK_COUNT] = newStreak

            val longest = preferences[PreferenceKeys.LONGEST_STREAK] ?: 0
            if (newStreak > longest) {
                preferences[PreferenceKeys.LONGEST_STREAK] = newStreak
            }
        }
    }

    suspend fun resetStreak() {
        setStreakCount(0)
    }

    suspend fun updateTotalReviewed(count: Int) {
        setTotalCardsReviewed(count)
    }

    suspend fun setFocusMode(active: Boolean) {
        setFocusModeActive(active)
    }

    suspend fun setUserName(name: String) {
        setCurrentUserName(name)
    }

    suspend fun updateCompanionDismissed(timestamp: Long) {
        setCompanionLastDismissed(timestamp)
    }

    // --- Direct Single Key Setters ---

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun setThemePreference(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.THEME_PREFERENCE] = theme
        }
    }

    suspend fun setDailyReviewGoal(goal: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.DAILY_REVIEW_GOAL] = goal
        }
    }

    suspend fun setReviewNotificationEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_ENABLED] = enabled
        }
    }

    suspend fun setReviewNotificationHour(hour: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_HOUR] = hour
        }
    }

    suspend fun setReviewNotificationMinute(minute: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.REVIEW_NOTIFICATION_MINUTE] = minute
        }
    }

    suspend fun setBreakReminderEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.BREAK_REMINDER_ENABLED] = enabled
        }
    }

    suspend fun setBreakIntervalMinutes(minutes: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.BREAK_INTERVAL_MINUTES] = minutes
        }
    }

    suspend fun setDefaultCategory(category: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.DEFAULT_CATEGORY] = category
        }
    }

    suspend fun setLastReviewDate(date: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.LAST_REVIEW_DATE] = date
        }
    }

    suspend fun setStreakCount(streak: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.STREAK_COUNT] = streak
        }
    }

    suspend fun setLongestStreak(streak: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.LONGEST_STREAK] = streak
        }
    }

    suspend fun setTotalCardsReviewed(count: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.TOTAL_CARDS_REVIEWED] = count
        }
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.SOUND_ENABLED] = enabled
        }
    }

    suspend fun setHapticEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.HAPTIC_ENABLED] = enabled
        }
    }

    suspend fun setFontSizePreference(size: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.FONT_SIZE_PREFERENCE] = size
        }
    }

    suspend fun setFocusModeActive(active: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.FOCUS_MODE_ACTIVE] = active
        }
    }

    suspend fun setCurrentUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.CURRENT_USER_NAME] = name
        }
    }

    suspend fun setCompanionLastDismissed(timestamp: Long) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.COMPANION_LAST_DISMISSED] = timestamp
        }
    }
}
