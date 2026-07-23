package com.revizen.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.revizen.app.core.navigation.BottomNavItem
import com.revizen.app.core.navigation.ReviZenNavGraph
import com.revizen.app.core.ui.components.BottomNavBar
import com.revizen.app.features.auth.ui.AuthViewModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Main screen scaffold containing the bottom navigation bar and NavHost.
 * The bottom bar is hidden on non-tab screens (detail, add, edit, auth, onboarding, etc.).
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel,
    dueCardCount: StateFlow<Int>,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val dueCount by dueCardCount.collectAsState()

    // Routes where the bottom bar should be visible
    val bottomBarRoutes = BottomNavItem.items.map { it.route }.toSet()
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavBar(
                    navController = navController,
                    dueCardCount = dueCount
                )
            }
        }
    ) { innerPadding ->
        ReviZenNavGraph(
            navController = navController,
            startDestination = startDestination,
            authViewModel = authViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
