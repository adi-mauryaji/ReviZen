package com.revizen.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.revizen.app.core.navigation.ReviZenNavGraph
import com.revizen.app.core.navigation.ReviZenRoute
import com.revizen.app.core.ui.theme.ReviZenTheme
import com.revizen.app.features.auth.domain.AuthState
import com.revizen.app.features.auth.ui.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entrance activity for ReviZen.
 * Inherits from FragmentActivity to enable AndroidX BiometricPrompt.
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply core-splashscreen Native API
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        enableEdgeToEdge()

        setContent {
            ReviZenTheme {
                val authState by authViewModel.authState.collectAsState()
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Decide starting screen depending on authState
                    val startDest = when (authState) {
                        is AuthState.SetupRequired -> ReviZenRoute.AuthSetup.route
                        is AuthState.Unlocked -> ReviZenRoute.Home.route
                        else -> ReviZenRoute.AuthLock.route
                    }

                    ReviZenNavGraph(
                        navController = navController,
                        startDestination = startDest,
                        authViewModel = authViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Lock screen checking on app resume
        authViewModel.checkAutoLock()
        authViewModel.updateActivity()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        // Keep active session alive
        authViewModel.updateActivity()
    }
}
