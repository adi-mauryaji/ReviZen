package com.revizen.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Holds route, icons, and label for each bottom navigation tab.
 */
data class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    companion object {
        val items = listOf(
            BottomNavItem(
                route = ReviZenRoute.Home.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                label = "Home"
            ),
            BottomNavItem(
                route = ReviZenRoute.MemoryList.route,
                selectedIcon = Icons.Filled.Psychology,
                unselectedIcon = Icons.Outlined.Psychology,
                label = "Memory"
            ),
            BottomNavItem(
                route = ReviZenRoute.ReviewSession.route,
                selectedIcon = Icons.Filled.RateReview,
                unselectedIcon = Icons.Outlined.RateReview,
                label = "Review"
            ),
            BottomNavItem(
                route = ReviZenRoute.HabitList.route,
                selectedIcon = Icons.Filled.CheckCircle,
                unselectedIcon = Icons.Outlined.CheckCircle,
                label = "Habits"
            ),
            BottomNavItem(
                route = ReviZenRoute.Analytics.route,
                selectedIcon = Icons.Filled.BarChart,
                unselectedIcon = Icons.Outlined.BarChart,
                label = "Analytics"
            )
        )
    }
}
