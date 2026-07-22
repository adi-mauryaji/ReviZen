package com.revizen.app.core.utils

import androidx.compose.runtime.Immutable

/**
 * Universal state wrapper for UI components, facilitating clean Loading/Success/Error/Empty states.
 */
@Immutable
sealed interface UiState<out T> {

    @Immutable
    object Loading : UiState<Nothing>

    @Immutable
    data class Success<out T>(val data: T) : UiState<T>

    @Immutable
    data class Error(val message: String, val cause: Throwable? = null) : UiState<Nothing>

    @Immutable
    object Empty : UiState<Nothing>
}
