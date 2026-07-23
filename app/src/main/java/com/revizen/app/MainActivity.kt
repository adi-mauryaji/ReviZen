package com.revizen.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.revizen.app.core.datastore.UserPreferencesRepository
import com.revizen.app.core.database.dao.MemoryCardDao
import com.revizen.app.core.navigation.ReviZenRoute
import com.revizen.app.core.ui.theme.ReviZenTheme
import com.revizen.app.features.auth.domain.AuthState
import com.revizen.app.features.auth.ui.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * Main entrance activity for ReviZen.
 * Inherits from FragmentActivity to enable AndroidX BiometricPrompt.
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    @Inject lateinit var userPreferencesRepository: UserPreferencesRepository
    @Inject lateinit var memoryCardDao: MemoryCardDao

    private val _dueCardCount = MutableStateFlow(0)
    private val dueCardCount: StateFlow<Int> = _dueCardCount

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply core-splashscreen Native API
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
        enableEdgeToEdge()

        // Observe due card count
        lifecycleScope.launch {
            memoryCardDao.getDueCount(System.currentTimeMillis()).collect { count ->
                _dueCardCount.value = count
            }
        }

        setContent {
            ReviZenTheme {
                val authState by authViewModel.authState.collectAsState()
                val userPrefs by userPreferencesRepository.userPreferences
                    .collectAsState(initial = null)
                val navController = rememberNavController()

                // Decide starting screen depending on authState and onboarding
                val startDest = when (authState) {
                    is AuthState.SetupRequired -> ReviZenRoute.AuthSetup.route
                    is AuthState.Unlocked -> {
                        if (userPrefs?.onboardingCompleted == false) {
                            ReviZenRoute.Onboarding.route
                        } else {
                            ReviZenRoute.Home.route
                        }
                    }
                    else -> ReviZenRoute.AuthLock.route
                }

                MainScreen(
                    navController = navController,
                    startDestination = startDest,
                    authViewModel = authViewModel,
                    dueCardCount = dueCardCount
                )
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
