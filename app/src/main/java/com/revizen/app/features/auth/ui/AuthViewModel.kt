package com.revizen.app.features.auth.ui

import androidx.lifecycle.viewModelScope
import com.revizen.app.core.utils.BaseViewModel
import com.revizen.app.features.auth.data.AuthPreferences
import com.revizen.app.features.auth.data.AuthRepository
import com.revizen.app.features.auth.domain.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel orchestrating authentication transitions: Setup PIN, Lock Screen, and Biometric challenge.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authPreferences: AuthPreferences
) : BaseViewModel() {

    private val _isSessionUnlocked = MutableStateFlow(false)

    /**
     * Determines overall AuthState by combining persistent storage flags and session state.
     */
    val authState: StateFlow<AuthState> = combine(
        authPreferences.isPinSet,
        _isSessionUnlocked
    ) { isPinSet, isUnlocked ->
        when {
            !isPinSet -> AuthState.SetupRequired
            isUnlocked -> AuthState.Unlocked
            else -> AuthState.Locked
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AuthState.Locked
    )

    val isBiometricsEnabled: StateFlow<Boolean> = authPreferences.isBiometricsEnabled
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val isBiometricHardwareAvailable: Boolean
        get() = authRepository.isBiometricHardwareAvailable()

    /**
     * Attempts to unlock the app using a passcode (PIN).
     */
    fun unlockWithPin(pin: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        safeLaunch(
            onError = { msg, _ -> onError(msg) }
        ) {
            val isValid = authRepository.verifyPin(pin)
            if (isValid) {
                _isSessionUnlocked.value = true
                authPreferences.updateLastActiveTimestamp(System.currentTimeMillis())
                onSuccess()
            } else {
                onError("Incorrect PIN. Please try again.")
            }
        }
    }

    /**
     * Sets up a brand new passcode (PIN).
     */
    fun registerNewPin(pin: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        safeLaunch(
            onError = { msg, _ -> onError(msg) }
        ) {
            authRepository.savePin(pin)
            _isSessionUnlocked.value = true
            authPreferences.updateLastActiveTimestamp(System.currentTimeMillis())
            onSuccess()
        }
    }

    /**
     * Sets the preference for biometric authorization.
     */
    fun setBiometricsEnabled(enabled: Boolean) {
        safeLaunch {
            authPreferences.setBiometricsEnabled(enabled)
        }
    }

    /**
     * Manually triggers unlocking state when biometric check passes.
     */
    fun unlockWithBiometrics() {
        safeLaunch {
            _isSessionUnlocked.value = true
            authPreferences.updateLastActiveTimestamp(System.currentTimeMillis())
        }
    }

    /**
     * Checks if the app should auto-lock based on elapsed idle time (e.g. 5 minutes).
     */
    fun checkAutoLock(timeoutMillis: Long = 300000L) {
        safeLaunch {
            viewModelScope.launch {
                authPreferences.lastActiveTimestamp.collect { lastActive ->
                    val now = System.currentTimeMillis()
                    if (lastActive > 0 && (now - lastActive) > timeoutMillis) {
                        _isSessionUnlocked.value = false
                    }
                }
            }
        }
    }

    /**
     * Updates activity timestamp to keep the session alive.
     */
    fun updateActivity() {
        safeLaunch {
            authPreferences.updateLastActiveTimestamp(System.currentTimeMillis())
        }
    }
}
