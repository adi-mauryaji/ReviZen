package com.revizen.app.core.database.model

/**
 * Projection class for daily study time aggregation.
 * Used by analytics to chart study minutes per day.
 */
data class DailyStudyStats(
    val date: Long,
    val minutes: Int
)
