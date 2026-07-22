package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.MemoryCard
import com.revizen.app.core.database.model.CategoryCount
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [MemoryCard] operations.
 * Provides reactive Flow-based queries for the spaced repetition engine.
 */
@Dao
interface MemoryCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: MemoryCard): Long

    @Update
    suspend fun update(card: MemoryCard)

    @Delete
    suspend fun delete(card: MemoryCard)

    @Query("SELECT * FROM memory_cards WHERE is_archived = 0 ORDER BY updated_at DESC")
    fun getAll(): Flow<List<MemoryCard>>

    @Query("SELECT * FROM memory_cards WHERE id = :id")
    fun getById(id: Long): Flow<MemoryCard?>

    @Query("SELECT * FROM memory_cards WHERE next_review_at <= :currentTime AND is_archived = 0 ORDER BY next_review_at ASC")
    fun getDueForReview(currentTime: Long): Flow<List<MemoryCard>>

    @Query("SELECT * FROM memory_cards WHERE category = :category AND is_archived = 0 ORDER BY updated_at DESC")
    fun getByCategory(category: String): Flow<List<MemoryCard>>

    @Query("SELECT * FROM memory_cards WHERE (title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%') AND is_archived = 0 ORDER BY updated_at DESC")
    fun searchCards(query: String): Flow<List<MemoryCard>>

    @Query("SELECT * FROM memory_cards WHERE is_favorite = 1 AND is_archived = 0 ORDER BY updated_at DESC")
    fun getFavorites(): Flow<List<MemoryCard>>

    @Query("SELECT COUNT(*) FROM memory_cards WHERE next_review_at <= :currentTime AND is_archived = 0")
    fun getDueCount(currentTime: Long): Flow<Int>

    @Query("SELECT category, COUNT(*) as count FROM memory_cards WHERE is_archived = 0 GROUP BY category ORDER BY count DESC")
    fun countByCategory(): Flow<List<CategoryCount>>
}
