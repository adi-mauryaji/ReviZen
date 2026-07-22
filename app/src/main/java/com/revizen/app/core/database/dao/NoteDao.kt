package com.revizen.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.revizen.app.core.database.entity.Note
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for [Note] operations.
 * Supports pinning, full-text search, and folder-based organization.
 */
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes ORDER BY is_pinned DESC, updated_at DESC")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE is_pinned = 1 ORDER BY updated_at DESC")
    fun getPinned(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY updated_at DESC")
    fun searchNotes(query: String): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Long): Flow<Note?>
}
