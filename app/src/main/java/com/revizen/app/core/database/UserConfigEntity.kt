package com.revizen.app.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity holding high-level local user configurations.
 */
@Entity(tableName = "user_config")
data class UserConfigEntity(
    @PrimaryKey val id: String = "config",
    val username: String = "User",
    val onboardingCompleted: Boolean = false,
    val biometricsEnabled: Boolean = false
)
