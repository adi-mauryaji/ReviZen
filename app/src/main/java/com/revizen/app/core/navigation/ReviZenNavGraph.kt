package com.revizen.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revizen.app.core.ui.components.PlaceholderScreen
import com.revizen.app.features.auth.ui.AuthViewModel
import com.revizen.app.features.auth.ui.LockScreenRoute
import com.revizen.app.features.auth.ui.SetupPinScreenRoute

/**
 * Standard Navigation Graph defining transitions across all features.
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

        composable(ReviZenRoute.Onboarding.route) {
            PlaceholderScreen(
                title = "Welcome to ReviZen",
                description = "Learn how to optimize your cognitive abilities offline.",
                onNavigateBack = {
                    navController.navigate(ReviZenRoute.AuthSetup.route)
                }
            )
        }

        composable(ReviZenRoute.Home.route) {
            PlaceholderScreen(
                title = "Dashboard",
                description = "Welcome back to your cognitive workspace.",
                onNavigateBack = {
                    navController.navigate(ReviZenRoute.Settings.route)
                }
            )
        }

        composable(ReviZenRoute.Habits.route) {
            PlaceholderScreen(
                title = "Habits Tracker",
                description = "Track your routines offline with privacy."
            )
        }

        composable(ReviZenRoute.Memory.route) {
            PlaceholderScreen(
                title = "Memory Palace",
                description = "Create offline memory rooms and flashcards."
            )
        }

        composable(ReviZenRoute.Notes.route) {
            PlaceholderScreen(
                title = "Personal Notes",
                description = "Secure markdown notes with smart categorization."
            )
        }

        composable(ReviZenRoute.Review.route) {
            PlaceholderScreen(
                title = "Spaced Repetition Review",
                description = "Review items with customized scheduling algorithms."
            )
        }

        composable(ReviZenRoute.Analytics.route) {
            PlaceholderScreen(
                title = "Progression Analytics",
                description = "Inspect local performance stats and learning curves."
            )
        }

        composable(ReviZenRoute.Settings.route) {
            PlaceholderScreen(
                title = "Settings",
                description = "Manage local backups, dark mode, and authentication preferences."
            )
        }
    }
}
