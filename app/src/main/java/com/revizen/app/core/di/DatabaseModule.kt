package com.revizen.app.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revizen.app.core.database.ReviZenDatabase
import com.revizen.app.core.database.UserConfigDao
import com.revizen.app.core.database.dao.AppSettingsDao
import com.revizen.app.core.database.dao.GoalDao
import com.revizen.app.core.database.dao.HabitDao
import com.revizen.app.core.database.dao.HabitLogDao
import com.revizen.app.core.database.dao.MemoryCardDao
import com.revizen.app.core.database.dao.NoteDao
import com.revizen.app.core.database.dao.ReviewSessionDao
import com.revizen.app.core.database.dao.StudySessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module to configure the local database instance and all DAOs.
 * Enables WAL journal mode for improved concurrent read/write performance.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ReviZenDatabase {
        return Room.databaseBuilder(
            context,
            ReviZenDatabase::class.java,
            ReviZenDatabase.DATABASE_NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserConfigDao(database: ReviZenDatabase): UserConfigDao {
        return database.userConfigDao()
    }

    @Provides
    @Singleton
    fun provideMemoryCardDao(database: ReviZenDatabase): MemoryCardDao {
        return database.memoryCardDao()
    }

    @Provides
    @Singleton
    fun provideReviewSessionDao(database: ReviZenDatabase): ReviewSessionDao {
        return database.reviewSessionDao()
    }

    @Provides
    @Singleton
    fun provideHabitDao(database: ReviZenDatabase): HabitDao {
        return database.habitDao()
    }

    @Provides
    @Singleton
    fun provideHabitLogDao(database: ReviZenDatabase): HabitLogDao {
        return database.habitLogDao()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: ReviZenDatabase): NoteDao {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideStudySessionDao(database: ReviZenDatabase): StudySessionDao {
        return database.studySessionDao()
    }

    @Provides
    @Singleton
    fun provideGoalDao(database: ReviZenDatabase): GoalDao {
        return database.goalDao()
    }

    @Provides
    @Singleton
    fun provideAppSettingsDao(database: ReviZenDatabase): AppSettingsDao {
        return database.appSettingsDao()
    }
}
