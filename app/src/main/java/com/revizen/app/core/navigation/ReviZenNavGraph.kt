package com.revizen.app.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.revizen.app.features.auth.ui.AuthViewModel
import com.revizen.app.features.auth.ui.LockScreenRoute
import com.revizen.app.features.auth.ui.SetupPinScreenRoute

/**
 * Placeholder composable shown for all screens that are not yet implemented.
 */
@Composable
private fun RoutePlaceholder(routeName: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = routeName,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

/**
 * Main navigation graph defining transitions across all ReviZen features.
 */
@Composable
fun ReviZenNavGraph(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- Auth ---
        composable(ReviZenRoute.AuthLock.route) {
            LockScreenRoute(
                viewModel = authViewModel,
                onUnlockSuccess = {
                    navController.navigate(ReviZenRoute.Home.route) {
                        popUpTo(ReviZenRoute.AuthLock.route) { inclusive = true }
                    }
                }
            )
        }

        composable(ReviZenRoute.AuthSetup.route) {
            SetupPinScreenRoute(
                viewModel = authViewModel,
                onSetupSuccess = {
                    navController.navigate(ReviZenRoute.Home.route) {
                        popUpTo(ReviZenRoute.AuthSetup.route) { inclusive = true }
                    }
                }
            )
        }

        // --- Onboarding ---
        composable(ReviZenRoute.Onboarding.route) {
            RoutePlaceholder("onboarding")
        }

        // --- Main Tabs ---
        composable(ReviZenRoute.Home.route) {
            RoutePlaceholder("home")
        }

        // --- Memory ---
        composable(ReviZenRoute.MemoryList.route) {
            RoutePlaceholder("memory_list")
        }

        composable(
            route = ReviZenRoute.MemoryDetail.route,
            arguments = listOf(navArgument("cardId") { type = NavType.LongType })
        ) {
            RoutePlaceholder("memory_detail")
        }

        composable(
            route = ReviZenRoute.AddMemory.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            RoutePlaceholder("add_memory")
        }

        composable(
            route = ReviZenRoute.EditMemory.route,
            arguments = listOf(navArgument("cardId") { type = NavType.LongType })
        ) {
            RoutePlaceholder("edit_memory")
        }

        // --- Review ---
        composable(ReviZenRoute.ReviewSession.route) {
            RoutePlaceholder("review_session")
        }

        composable(ReviZenRoute.ReviewResult.route) {
            RoutePlaceholder("review_result")
        }

        // --- Habits ---
        composable(ReviZenRoute.HabitList.route) {
            RoutePlaceholder("habit_list")
        }

        composable(
            route = ReviZenRoute.HabitDetail.route,
            arguments = listOf(navArgument("habitId") { type = NavType.LongType })
        ) {
            RoutePlaceholder("habit_detail")
        }

        composable(ReviZenRoute.AddHabit.route) {
            RoutePlaceholder("add_habit")
        }

        // --- Notes ---
        composable(ReviZenRoute.NoteList.route) {
            RoutePlaceholder("note_list")
        }

        composable(
            route = ReviZenRoute.NoteDetail.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) {
            RoutePlaceholder("note_detail")
        }

        composable(ReviZenRoute.AddNote.route) {
            RoutePlaceholder("add_note")
        }

        // --- Analytics & Goals ---
        composable(ReviZenRoute.Analytics.route) {
            RoutePlaceholder("analytics")
        }

        composable(ReviZenRoute.Goals.route) {
            RoutePlaceholder("goals")
        }

        // --- Settings & Profile ---
        composable(ReviZenRoute.Settings.route) {
            RoutePlaceholder("settings")
        }

        composable(ReviZenRoute.Profile.route) {
            RoutePlaceholder("profile")
        }

        // --- Utility Screens ---
        composable(ReviZenRoute.Search.route) {
            RoutePlaceholder("search")
        }

        composable(ReviZenRoute.Focus.route) {
            RoutePlaceholder("focus")
        }

        composable(
            route = ReviZenRoute.FeynmanTest.route,
            arguments = listOf(navArgument("cardId") { type = NavType.LongType })
        ) {
            RoutePlaceholder("feynman_test")
        }

        composable(ReviZenRoute.Graph.route) {
            RoutePlaceholder("graph")
        }

        composable(ReviZenRoute.InterviewHome.route) {
            RoutePlaceholder("interview_home")
        }

        composable(ReviZenRoute.Planner.route) {
            RoutePlaceholder("planner")
        }

        composable(ReviZenRoute.Palace.route) {
            RoutePlaceholder("palace")
        }
    }
}
