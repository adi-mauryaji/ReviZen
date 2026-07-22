package com.revizen.app.core.navigation

/**
 * Defines compile-safe navigation routes for the ReviZen App.
 */
sealed class ReviZenRoute(val route: String) {
    object Onboarding : ReviZenRoute("onboarding")
    object AuthLock : ReviZenRoute("auth_lock")
    object AuthSetup : ReviZenRoute("auth_setup")
    object Home : ReviZenRoute("home")
    object Habits : ReviZenRoute("habits")
    object Memory : ReviZenRoute("memory")
    object Notes : ReviZenRoute("notes")
    object Review : ReviZenRoute("review")
    object Analytics : ReviZenRoute("analytics")
    object Settings : ReviZenRoute("settings")
}
