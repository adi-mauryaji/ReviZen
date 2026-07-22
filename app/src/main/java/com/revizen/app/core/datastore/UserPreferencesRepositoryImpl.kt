package com.revizen.app.core.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [UserPreferencesRepository] delegating directly to [ReviZenDataStore].
 */
@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: ReviZenDataStore
) : UserPreferencesRepository {

    override val userPreferences: Flow<UserPreferences> = dataStore.userPreferences

    override suspend fun updateTheme(theme: String) {
        dataStore.updateTheme(theme)
    }

    override suspend fun completeOnboarding() {
        dataStore.completeOnboarding()
    }

    override suspend fun updateDailyGoal(goal: Int) {
        dataStore.updateDailyGoal(goal)
    }

    override suspend fun updateNotificationSettings(enabled: Boolean, hour: Int, minute: Int) {
        dataStore.updateNotificationSettings(enabled, hour, minute)
    }

    override suspend fun incrementStreak() {
        dataStore.incrementStreak()
    }

    override suspend fun resetStreak() {
        dataStore.resetStreak()
    }

    override suspend fun updateTotalReviewed(count: Int) {
        dataStore.updateTotalReviewed(count)
    }

    override suspend fun setFocusMode(active: Boolean) {
        dataStore.setFocusMode(active)
    }

    override suspend fun setUserName(name: String) {
        dataStore.setUserName(name)
    }

    override suspend fun updateCompanionDismissed(timestamp: Long) {
        dataStore.updateCompanionDismissed(timestamp)
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.setOnboardingCompleted(completed)
    }

    override suspend fun setThemePreference(theme: String) {
        dataStore.setThemePreference(theme)
    }

    override suspend fun setDailyReviewGoal(goal: Int) {
        dataStore.setDailyReviewGoal(goal)
    }

    override suspend fun setReviewNotificationEnabled(enabled: Boolean) {
        dataStore.setReviewNotificationEnabled(enabled)
    }

    override suspend fun setReviewNotificationHour(hour: Int) {
        dataStore.setReviewNotificationHour(hour)
    }

    override suspend fun setReviewNotificationMinute(minute: Int) {
        dataStore.setReviewNotificationMinute(minute)
    }

    override suspend fun setBreakReminderEnabled(enabled: Boolean) {
        dataStore.setBreakReminderEnabled(enabled)
    }

    override suspend fun setBreakIntervalMinutes(minutes: Int) {
        dataStore.setBreakIntervalMinutes(minutes)
    }

    override suspend fun setDefaultCategory(category: String) {
        dataStore.setDefaultCategory(category)
    }

    override suspend fun setLastReviewDate(date: Long) {
        dataStore.setLastReviewDate(date)
    }

    override suspend fun setStreakCount(streak: Int) {
        dataStore.setStreakCount(streak)
    }

    override suspend fun setLongestStreak(streak: Int) {
        dataStore.setLongestStreak(streak)
    }

    override suspend fun setTotalCardsReviewed(count: Int) {
        dataStore.setTotalCardsReviewed(count)
    }

    override suspend fun setSoundEnabled(enabled: Boolean) {
        dataStore.setSoundEnabled(enabled)
    }

    override suspend fun setHapticEnabled(enabled: Boolean) {
        dataStore.setHapticEnabled(enabled)
    }

    override suspend fun setFontSizePreference(size: String) {
        dataStore.setFontSizePreference(size)
    }

    override suspend fun setFocusModeActive(active: Boolean) {
        dataStore.setFocusModeActive(active)
    }

    override suspend fun setCurrentUserName(name: String) {
        dataStore.setCurrentUserName(name)
    }

    override suspend fun setCompanionLastDismissed(timestamp: Long) {
        dataStore.setCompanionLastDismissed(timestamp)
    }
}
