package com.revizen.app.core.database.model

/**
 * Projection class for counting cards per category.
 * Used by [MemoryCardDao.countByCategory].
 */
data class CategoryCount(
    val category: String,
    val count: Int
)
