package com.revizen.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.revizen.app.core.database.dao.AppSettingsDao
import com.revizen.app.core.database.dao.GoalDao
import com.revizen.app.core.database.dao.HabitDao
import com.revizen.app.core.database.dao.HabitLogDao
import com.revizen.app.core.database.dao.MemoryCardDao
import com.revizen.app.core.database.dao.NoteDao
import com.revizen.app.core.database.dao.ReviewSessionDao
import com.revizen.app.core.database.dao.StudySessionDao
import com.revizen.app.core.database.entity.AppSettings
import com.revizen.app.core.database.entity.Goal
import com.revizen.app.core.database.entity.Habit
import com.revizen.app.core.database.entity.HabitLog
import com.revizen.app.core.database.entity.MemoryCard
import com.revizen.app.core.database.entity.Note
import com.revizen.app.core.database.entity.ReviewSession
import com.revizen.app.core.database.entity.StudySession

/**
 * Single local offline Room database representing ReviZen local storage.
 * WAL journal mode is enabled via the Hilt DatabaseModule builder for performance.
 */
@Database(
    entities = [
        MemoryCard::class,
        ReviewSession::class,
        Habit::class,
        HabitLog::class,
        Note::class,
        StudySession::class,
        Goal::class,
        AppSettings::class,
        UserConfigEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ReviZenDatabase : RoomDatabase() {

    abstract fun memoryCardDao(): MemoryCardDao
    abstract fun reviewSessionDao(): ReviewSessionDao
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
    abstract fun noteDao(): NoteDao
    abstract fun studySessionDao(): StudySessionDao
    abstract fun goalDao(): GoalDao
    abstract fun appSettingsDao(): AppSettingsDao
    abstract fun userConfigDao(): UserConfigDao

    companion object {
        const val DATABASE_NAME = "revizen.db"
    }
}
