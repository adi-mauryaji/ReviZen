package com.revizen.app.core.navigation

/**
 * Defines compile-safe navigation routes for the ReviZen App.
 * Routes with path arguments use {argName} syntax; companion helpers build concrete paths.
 */
sealed class ReviZenRoute(val route: String) {

    // --- Auth & Onboarding ---
    object Onboarding : ReviZenRoute("onboarding")
    object AuthLock : ReviZenRoute("auth_lock")
    object AuthSetup : ReviZenRoute("auth_setup")

    // --- Main Tabs ---
    object Home : ReviZenRoute("home")

    // --- Memory / Flashcards ---
    object MemoryList : ReviZenRoute("memory_list")
    object MemoryDetail : ReviZenRoute("memory_detail/{cardId}") {
        fun argsOf(cardId: Long): String = "memory_detail/$cardId"
    }
    object AddMemory : ReviZenRoute("add_memory?category={category}") {
        fun argsOf(category: String? = null): String =
            if (category != null) "add_memory?category=$category" else "add_memory"
    }
    object EditMemory : ReviZenRoute("edit_memory/{cardId}") {
        fun argsOf(cardId: Long): String = "edit_memory/$cardId"
    }

    // --- Review ---
    object ReviewSession : ReviZenRoute("review_session")
    object ReviewResult : ReviZenRoute("review_result")

    // --- Habits ---
    object HabitList : ReviZenRoute("habit_list")
    object HabitDetail : ReviZenRoute("habit_detail/{habitId}") {
        fun argsOf(habitId: Long): String = "habit_detail/$habitId"
    }
    object AddHabit : ReviZenRoute("add_habit")

    // --- Notes ---
    object NoteList : ReviZenRoute("note_list")
    object NoteDetail : ReviZenRoute("note_detail/{noteId}") {
        fun argsOf(noteId: Long): String = "note_detail/$noteId"
    }
    object AddNote : ReviZenRoute("add_note")

    // --- Analytics & Goals ---
    object Analytics : ReviZenRoute("analytics")
    object Goals : ReviZenRoute("goals")

    // --- Settings & Profile ---
    object Settings : ReviZenRoute("settings")
    object Profile : ReviZenRoute("profile")

    // --- Utility Screens ---
    object Search : ReviZenRoute("search")
    object Focus : ReviZenRoute("focus")
    object FeynmanTest : ReviZenRoute("feynman_test/{cardId}") {
        fun argsOf(cardId: Long): String = "feynman_test/$cardId"
    }
    object Graph : ReviZenRoute("graph")
    object InterviewHome : ReviZenRoute("interview_home")
    object Planner : ReviZenRoute("planner")
    object Palace : ReviZenRoute("palace")
}
