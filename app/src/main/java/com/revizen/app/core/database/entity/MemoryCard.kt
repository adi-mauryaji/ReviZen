package com.revizen.app.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Core flashcard entity used by the spaced repetition engine.
 * Stores card content, scheduling metadata (SM-2 algorithm fields),
 * and optional cognitive learning aids (Feynman summaries, mnemonics, analogies).
 */
@Entity(tableName = "memory_cards")
data class MemoryCard(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,
    val content: String,

    /** e.g., "text", "markdown", "image_ref" */
    @ColumnInfo(name = "content_type")
    val contentType: String = "text",

    val category: String = "",

    /** JSON-encoded list of tag strings */
    val tags: String = "[]",

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    // --- SM-2 Scheduling Fields ---

    @ColumnInfo(name = "last_reviewed_at")
    val lastReviewedAt: Long = 0L,

    @ColumnInfo(name = "next_review_at")
    val nextReviewAt: Long = 0L,

    @ColumnInfo(name = "interval_days")
    val intervalDays: Int = 0,

    @ColumnInfo(name = "ease_factor")
    val easeFactor: Float = 2.5f,

    @ColumnInfo(name = "repetition_count")
    val repetitionCount: Int = 0,

    @ColumnInfo(name = "lapse_count")
    val lapseCount: Int = 0,

    @ColumnInfo(name = "memory_strength")
    val memoryStrength: Float = 0f,

    // --- Flags ---

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "is_archived")
    val isArchived: Boolean = false,

    // --- Cognitive Learning Aids ---

    @ColumnInfo(name = "source_tag")
    val sourceTag: String = "",

    @ColumnInfo(name = "feynman_summary")
    val feynmanSummary: String = "",

    val mnemonic: String = "",

    @ColumnInfo(name = "analogy_note")
    val analogyNote: String = ""
)
