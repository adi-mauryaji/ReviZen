package com.revizen.app.core.utils

/**
 * Base exception class representing domain-specific errors in ReviZen.
 */
sealed class ReviZenException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause) {

    /** Exception during offline database queries or operations */
    class DatabaseException(message: String, cause: Throwable? = null) : ReviZenException(message, cause)

    /** Exception related to Biometric or PIN authentication */
    class AuthException(message: String, cause: Throwable? = null) : ReviZenException(message, cause)

    /** Exception when trying to access or parse invalid files/preferences */
    class PreferencesException(message: String, cause: Throwable? = null) : ReviZenException(message, cause)

    /** Unknown or unhandled generic exception */
    class UnknownException(message: String, cause: Throwable? = null) : ReviZenException(message, cause)
}
