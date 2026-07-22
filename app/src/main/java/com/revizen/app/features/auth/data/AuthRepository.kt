package com.revizen.app.features.auth.data

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.revizen.app.core.utils.ReviZenException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles secure storage of user passcode (PIN) and checks biometric hardware capability.
 * Employs AndroidX Security-Crypto library to safeguard secrets.
 */
@Singleton
class AuthRepository @Inject constructor(
    private val context: Context,
    private val authPreferences: AuthPreferences
) {
    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val securePrefs by lazy {
        EncryptedSharedPreferences.create(
            context,
            "secure_auth_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private const val KEY_PIN_HASH = "encrypted_pin_hash"
    }

    /**
     * Checks if biometric hardware is present, enrolled, and ready.
     */
    fun isBiometricHardwareAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        val status = biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
        return status == BiometricManager.BIOMETRIC_SUCCESS
    }

    /**
     * Saves the PIN securely using EncryptedSharedPreferences.
     */
    suspend fun savePin(pin: String) {
        if (pin.length < 4) {
            throw ReviZenException.AuthException("PIN must be at least 4 digits")
        }
        // Save hashed or direct PIN in encrypted storage
        securePrefs.edit().putString(KEY_PIN_HASH, hashPin(pin)).apply()
        authPreferences.setPinSet(true)
    }

    /**
     * Verifies if the entered PIN matches the saved PIN.
     */
    fun verifyPin(pin: String): Boolean {
        val savedHash = securePrefs.getString(KEY_PIN_HASH, null) ?: return false
        return savedHash == hashPin(pin)
    }

    /**
     * Clears PIN and disables authentication settings.
     */
    suspend fun clearPin() {
        securePrefs.edit().remove(KEY_PIN_HASH).apply()
        authPreferences.setPinSet(false)
        authPreferences.setBiometricsEnabled(false)
    }

    /**
     * Simple SHA-256 hashing for local storage verification.
     */
    private fun hashPin(pin: String): String {
        return try {
            val digest = java.security.MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(pin.toByteArray(Charsets.UTF_8))
            hashBytes.joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            throw ReviZenException.AuthException("Failed to hash PIN", e)
        }
    }
}
