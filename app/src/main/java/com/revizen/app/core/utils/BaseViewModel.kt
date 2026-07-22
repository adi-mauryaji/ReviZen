package com.revizen.app.core.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * BaseViewModel provides standardized Coroutine launching with automated error handling and logging.
 */
abstract class BaseViewModel : ViewModel() {

    protected open val tag: String = this.javaClass.simpleName

    /**
     * Helper to safely launch coroutines on the viewModelScope with exception catching.
     * Logs the error and runs an optional error callback.
     */
    protected fun safeLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onError: (String, Throwable) -> Unit = { _, _ -> },
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start) {
        try {
            block()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is ReviZenException -> e.message
                else -> "An unexpected error occurred"
            }
            Log.e(tag, "Exception in safeLaunch: $errorMessage", e)
            onError(errorMessage, e)
        }
    }
}
