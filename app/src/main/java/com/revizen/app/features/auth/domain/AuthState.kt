package com.revizen.app.features.auth.domain

import androidx.compose.runtime.Immutable

/**
 * State representing the authentication lock status of the app.
 */
@Immutable
sealed interface AuthState {

    /** First run: The user hasn't created a PIN yet */
    @Immutable
    object SetupRequired : AuthState

    /** Secure lock active: User must enter their PIN or authenticate via Biometrics */
    @Immutable
    object Locked : AuthState

    /** Session authorized: Full access to the application allowed */
    @Immutable
    object Unlocked : AuthState
}
