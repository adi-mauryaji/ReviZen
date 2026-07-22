package com.revizen.app.core.datastore

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface wrapping preferences data store for testability and domain access.
 */
interface UserPreferencesRepository {
    val userPreferences: Flow<UserPreferences>

    suspend fun updateTheme(theme: String)
    suspend fun completeOnboarding()
    suspend fun updateDailyGoal(goal: Int)
    suspend fun updateNotificationSettings(enabled: Boolean, hour: Int, minute: Int)
    suspend fun incrementStreak()
    suspend fun resetStreak()
    suspend fun updateTotalReviewed(count: Int)
    suspend fun setFocusMode(active: Boolean)
    suspend fun setUserName(name: String)
    suspend fun updateCompanionDismissed(timestamp: Long)

    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun setThemePreference(theme: String)
    suspend fun setDailyReviewGoal(goal: Int)
    suspend fun setReviewNotificationEnabled(enabled: Boolean)
    suspend fun setReviewNotificationHour(hour: Int)
    suspend fun setReviewNotificationMinute(minute: Int)
    suspend fun setBreakReminderEnabled(enabled: Boolean)
    suspend fun setBreakIntervalMinutes(minutes: Int)
    suspend fun setDefaultCategory(category: String)
    suspend fun setLastReviewDate(date: Long)
    suspend fun setStreakCount(streak: Int)
    suspend fun setLongestStreak(streak: Int)
    suspend fun setTotalCardsReviewed(count: Int)
    suspend fun setSoundEnabled(enabled: Boolean)
    suspend fun setHapticEnabled(enabled: Boolean)
    suspend fun setFontSizePreference(size: String)
    suspend fun setFocusModeActive(active: Boolean)
    suspend fun setCurrentUserName(name: String)
    suspend fun setCompanionLastDismissed(timestamp: Long)
}
