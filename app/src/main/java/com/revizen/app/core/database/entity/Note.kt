package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A markdown-capable note with tagging and card linking support.
 * Notes can be pinned, organized into folders, and linked to [MemoryCard] IDs.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val content: String = "",

    /** e.g., "markdown", "plain_text" */
    @ColumnInfo(name = "content_type")
    val contentType: String = "markdown",

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "is_pinned")
    val isPinned: Boolean = false,

    /** JSON-encoded list of tag strings */
    val tags: String = "[]",

    /** JSON-encoded list of linked MemoryCard IDs */
    @ColumnInfo(name = "linked_card_ids")
    val linkedCardIds: String = "[]",

    @ColumnInfo(name = "folder_id")
    val folderId: Long = 0L
)
