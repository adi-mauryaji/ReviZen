package com.revizen.app.core.database.model

/**
 * Projection class for review accuracy statistics.
 * Used by [ReviewSessionDao.getAccuracyStats].
 */
data class AccuracyStats(
    val totalReviews: Int,
    val correctCount: Int
)
