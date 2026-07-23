# Project Overview
- **Project Name**: ReviZen
- **Package Name**: `com.revizen.app`
- **Purpose**: A comprehensive study, review, and personal growth application. It helps users track habits, perform spaced repetition reviews, build memory palaces, take notes, and visualize learning progress via analytics.
- **Vision**: Ultimate offline cognitive workspace.
- **Target Platform**: Android API 26+ | minSdk 26 | targetSdk 34 | compileSdk 34

---

# Architecture
- **Overall architecture**: Feature-based Clean Architecture with MVVM (Model-View-ViewModel).
- **Layer responsibilities**:
  - **`core`**: App-wide cross-cutting concerns (Database, DataStore, Dependency Injection, Navigation, UI Theme, Utils).
  - **`features`**: Contains subfolders for each distinct app feature (e.g., `habits`, `notes`, `review`, `auth`). Each feature is split internally into:
    - **`data`**: Local Room database entities, DAOs, repositories, and local preferences.
    - **`domain`**: Use cases, business logic, state models.
    - **`ui`**: ViewModels, Composables, and visual states.
  - **`workers`**: Periodic and background tasks (WorkManager).

---

# Package Structure
```
com.revizen.app/
├── core/
│   ├── database/       # Room Database, DAOs, base entities
│   ├── datastore/      # DataStore preferences (e.g. settings)
│   ├── di/             # Global Hilt dependency modules
│   ├── navigation/     # NavHost controller, route setups
│   ├── ui/
│   │   ├── components/ # Reusable UI components
│   │   └── theme/      # Colors, typography scales, Material Theme definition
│   └── utils/          # Exceptions, UiStates, common ViewModel utilities
├── features/
│   ├── home/           # Dashboard screen
│   ├── habits/         # Habit tracking
│   ├── memory/         # Offline flashcards and memory palace modules
│   ├── notes/          # Markdown secure note taking
│   ├── review/         # Spaced repetition engine
│   ├── analytics/      # Progression graphs & statistics
│   ├── settings/       # Settings configurations
│   ├── onboarding/     # First-launch onboarding flows
│   └── auth/           # Secure local PIN and biometrics authentication
└── workers/            # Spaced repetition reminders and cleanups
```

---

# Theme Configuration
- **Primary Color**: Purple100 (`#6C63FF`)
- **Secondary Color**: Green100 (`#48BB78`)
- **Tertiary Color**: Amber100 (`#F6AD55`)
- **Error Color**: Red100 (`#FC8181`)
- **Ink Color**: `#1A1A2E` (Dark background)
- **Surface Color**: `#F1F5F9` (Light surface/background)
- **SurfaceDark Color**: `#16213E` (Dark card surfaces)

---

# Dependencies
The app dependencies configured in `libs.versions.toml`:
- **AndroidX Appcompat**: `1.7.0` (Provides FragmentActivity support for Biometrics)
- **Compose BOM**: `2024.06.00`
- **Navigation Compose**: `2.7.7`
- **Lifecycle Runtime & ViewModel Compose**: `2.8.2`
- **Room Database**: `2.6.1`
- **Dagger Hilt DI**: `2.51.1`
- **DataStore Preferences**: `1.1.1`
- **WorkManager**: `2.9.0`
- **Kotlin Serialization**: `1.7.1`
- **Core Splash Screen**: `1.0.1`
- **Lottie Compose**: `6.4.1`
- **Biometric**: `1.2.0-alpha05` (Offline authentication)
- **Security Crypto**: `1.1.0-alpha06` (Encrypted PIN storage)

---

# Coding Standards
- **Flow-Based States**: Use StateFlow for state management instead of LiveData.
- **Background Dispatchers**: All database reads/writes must run on `Dispatchers.IO`.
- **UI Architecture**: Unidirectional data flow. Screens observe ViewModel-emitted `UiState` values.
- **Compose Conventions**: Support both light/dark system settings. Never hardcode colors directly in composables.
- **Stable and Immutable annotations**: Ensure stable VM state emission to avoid redundant Compose recomposition.
- **Error Handling**: Custom `ReviZenException` subclasses for cleaner domain-level error handling. Catch in VMs using `safeLaunch`.

---

# Session History

### Session: 2026-07-18
- **User Request**: Bootstrap the ReviZen Android project from scratch based on KYROS_BOOT.md and implement secure Clerk authentication.
- **Decisions**: 
  - Since Clerk requires a cloud backend and internet connection, it directly violated the "100% offline, no backend, no accounts" constraint. In consultation with the user, Clerk was dropped in favor of a 100% offline biometric prompt + encrypted passcode lock.
- **Work Performed**:
  - Scaffolded the Kotlin Gradle DSL Android empty activity using the Android CLI tool.
  - RESTructured packages to `com.revizen.app` conforming to feature-by-layer Clean Architecture.
  - Setup versions catalog (`libs.versions.toml`) and app-level Gradle builds with Room, Hilt, Navigation Compose, WorkManager, Biometric, Security-Crypto, Lottie, and Splashscreen.
  - Configured core layout styling theme (colors, typography scales, dark/light theme composable).
  - Created base utilities (`UiState`, `ReviZenException`, `BaseViewModel`).
  - Implemented secure local PIN management and biometric verification flow under `features/auth/`.
  - Configured Hilt modules (`DatabaseModule`, `DataStoreModule`, `AuthModule`) and registered Application subclass in `AndroidManifest.xml` with permissions.
- **Files Created**:
  - `gradle/libs.versions.toml`
  - `build.gradle.kts`, `app/build.gradle.kts`
  - `app/src/main/AndroidManifest.xml`
  - `com/revizen/app/ReviZenApplication.kt`
  - `com/revizen/app/MainActivity.kt`
  - `com/revizen/app/core/ui/theme/{Color.kt, Type.kt, Theme.kt}`
  - `com/revizen/app/core/utils/{UiState.kt, ReviZenException.kt, BaseViewModel.kt}`
  - `com/revizen/app/core/ui/components/PlaceholderScreen.kt`
  - `com/revizen/app/core/navigation/{Routes.kt, ReviZenNavGraph.kt}`
  - `com/revizen/app/features/auth/data/{AuthPreferences.kt, AuthRepository.kt}`
  - `com/revizen/app/features/auth/domain/AuthState.kt`
  - `com/revizen/app/features/auth/ui/{AuthViewModel.kt, LockScreen.kt, SetupPinScreen.kt}`
  - `com/revizen/app/features/auth/di/AuthModule.kt`
  - `com/revizen/app/core/database/{UserConfigEntity.kt, UserConfigDao.kt, ReviZenDatabase.kt}`
  - `com/revizen/app/core/di/{DatabaseModule.kt, DataStoreModule.kt}`
  - `com/revizen/app/core/datastore/SettingsPreferences.kt`
  - `.agents/AGENTS.md`
  - `PROJECT_CONTEXT.md`

### Session: 2026-07-18 (Prompt 02 - Complete Room Database)
- **User Request**: Implement complete Room database with all 8 entities, all DAOs, TypeConverters, result models, and updated Hilt module.
- **Decisions**:
  - Kept existing `UserConfigEntity` and `UserConfigDao` for backward compatibility alongside the 8 new entities.
  - Used `exportSchema = true` to enable Room schema versioning for future migrations.
  - Enabled WAL journal mode via `RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING` for concurrent read/write performance.
  - Used `kotlinx.serialization.json` for TypeConverters (consistent with existing dependency).
  - All JSON-backed fields (tags, customDays, linkedCardIds, milestones) stored as raw JSON strings in the entity with TypeConverters available for domain-layer deserialization.
- **Database Configuration**:
  - **DB Name**: `revizen.db`
  - **DB Version**: 1
  - **Export Schema**: true
  - **Journal Mode**: WAL
- **Entities** (9 total, `core/database/entity/`):
  1. `MemoryCard` → `memory_cards` (SM-2 spaced repetition flashcards)
  2. `ReviewSession` → `review_sessions` (FK → memory_cards, CASCADE delete)
  3. `Habit` → `habits` (recurring habit definitions)
  4. `HabitLog` → `habit_logs` (FK → habits, CASCADE delete)
  5. `Note` → `notes` (markdown notes with card linking)
  6. `StudySession` → `study_sessions` (timed study blocks)
  7. `Goal` → `goals` (learning goals with milestones)
  8. `AppSettings` → `app_settings` (single-row settings, id=1)
  9. `UserConfigEntity` → `user_config` (existing from Prompt 01)
- **DAOs** (9 total, `core/database/dao/` + existing):
  1. `MemoryCardDao` — CRUD + getDueForReview, searchCards, getFavorites, getDueCount, countByCategory
  2. `ReviewSessionDao` — CRUD + getSessionsForCard, getSessionsInRange, getAccuracyStats
  3. `HabitDao` — CRUD + getActiveHabits, getById
  4. `HabitLogDao` — CRUD + getLogsForHabit, getLogsInRange, isCompletedToday
  5. `NoteDao` — CRUD + getPinned, searchNotes, getById
  6. `StudySessionDao` — CRUD + getInRange, getTotalStudyTime
  7. `GoalDao` — CRUD + getActive, getById
  8. `AppSettingsDao` — getSettings, upsertSettings
  9. `UserConfigDao` (existing from Prompt 01)
- **Result Models** (`core/database/model/`):
  - `CategoryCount` (category + count)
  - `AccuracyStats` (totalReviews + correctCount)
  - `DailyStudyStats` (date + minutes)
- **TypeConverters** (`core/database/Converters.kt`):
  - `List<String>` ↔ JSON String
  - `List<Long>` ↔ JSON String
  - `List<Int>` ↔ JSON String
- **Files Created**:
  - `core/database/entity/{MemoryCard.kt, ReviewSession.kt, Habit.kt, HabitLog.kt, Note.kt, StudySession.kt, Goal.kt, AppSettings.kt}`
  - `core/database/dao/{MemoryCardDao.kt, ReviewSessionDao.kt, HabitDao.kt, HabitLogDao.kt, NoteDao.kt, StudySessionDao.kt, GoalDao.kt, AppSettingsDao.kt}`
  - `core/database/model/{CategoryCount.kt, AccuracyStats.kt, DailyStudyStats.kt}`
  - `core/database/Converters.kt`
- **Files Modified**:
  - `core/database/ReviZenDatabase.kt` (all 9 entities, exportSchema=true, TypeConverters, 9 DAO accessors, DATABASE_NAME constant)
  - `core/di/DatabaseModule.kt` (provides all 9 DAOs, WAL journal mode, uses DATABASE_NAME constant)
  - `app/build.gradle.kts` (added KSP room.schemaLocation arg)

### Session: 2026-07-18 (Prompt 03 - Complete Preferences DataStore)
- **User Request**: Implement preferences DataStore with 19 keys, sensible defaults, repository interface and implementation, and Hilt setup.
- **Decisions**:
  - Expose preferences as a flow of a unified `UserPreferences` immutable data class.
  - Create `PreferenceKeys` object mapping to type-safe preference keys.
  - Implement direct single-key setters as well as custom multi-key setters (e.g. `updateNotificationSettings`, `incrementStreak`) in both `ReviZenDataStore` and `UserPreferencesRepository`.
  - Upgraded dependencies syntax in `app/build.gradle.kts` to replace hyphens with dots for Kotlin DSL compatibility.
- **Preference Keys & Types**:
  1. `ONBOARDING_COMPLETED` (Boolean, default: `false`)
  2. `THEME_PREFERENCE` (String, default: `"system"`)
  3. `DAILY_REVIEW_GOAL` (Int, default: `20`)
  4. `REVIEW_NOTIFICATION_ENABLED` (Boolean, default: `true`)
  5. `REVIEW_NOTIFICATION_HOUR` (Int, default: `9`)
  6. `REVIEW_NOTIFICATION_MINUTE` (Int, default: `0`)
  7. `BREAK_REMINDER_ENABLED` (Boolean, default: `true`)
  8. `BREAK_INTERVAL_MINUTES` (Int, default: `25`)
  9. `DEFAULT_CATEGORY` (String, default: `"General"`)
  10. `LAST_REVIEW_DATE` (Long, default: `0L`)
  11. `STREAK_COUNT` (Int, default: `0`)
  12. `LONGEST_STREAK` (Int, default: `0`)
  13. `TOTAL_CARDS_REVIEWED` (Int, default: `0`)
  14. `SOUND_ENABLED` (Boolean, default: `true`)
  15. `HAPTIC_ENABLED` (Boolean, default: `true`)
  16. `FONT_SIZE_PREFERENCE` (String, default: `"medium"`)
  17. `FOCUS_MODE_ACTIVE` (Boolean, default: `false`)
  18. `CURRENT_USER_NAME` (String, default: `"User"`)
  19. `COMPANION_LAST_DISMISSED` (Long, default: `0L`)
- **Files Created**:
  - `core/datastore/PreferenceKeys.kt`
  - `core/datastore/UserPreferences.kt`
  - `core/datastore/ReviZenDataStore.kt`
  - `core/datastore/UserPreferencesRepository.kt`
  - `core/datastore/UserPreferencesRepositoryImpl.kt`
- **Files Modified**:
  - `core/di/DataStoreModule.kt` (provides `DataStore<Preferences>`, `ReviZenDataStore`, and binds `UserPreferencesRepository`)
  - `app/build.gradle.kts` (removed compose-compiler plugin, added composeOptions block, fixed dependency dots syntax)
  - `build.gradle.kts` (removed compose-compiler plugin)
  - `gradle/libs.versions.toml` (removed compose-compiler plugin)
  - `PROJECT_CONTEXT.md`

### Session: 2026-07-23 (Prompt 04 - Complete Navigation System)
- **User Request**: Implement all routes, NavGraph, bottom nav bar, MainScreen scaffold. Placeholder composables only (no real screens yet).
- **Decisions**:
  - Used `sealed class ReviZenRoute(val route: String)` with nested objects for all 25 routes.
  - Routes with path arguments (e.g. `MemoryDetail`, `HabitDetail`, `NoteDetail`, `EditMemory`, `FeynmanTest`) include companion `argsOf()` helpers.
  - `AddMemory` route supports optional `category` query argument.
  - Start destination logic: if `AuthState.Unlocked` and `onboardingCompleted == false` → `Onboarding`; else `Home`.
  - Bottom nav bar hidden on non-tab screens via `AnimatedVisibility` slide animation.
  - Bottom nav uses `saveState`, `restoreState`, and `launchSingleTop` to prevent back-stack duplication.
  - Review tab badge shows due card count from `MemoryCardDao.getDueCount()`.
- **Routes Defined**:
  1. `Onboarding` → `"onboarding"`
  2. `AuthLock` → `"auth_lock"`
  3. `AuthSetup` → `"auth_setup"`
  4. `Home` → `"home"`
  5. `MemoryList` → `"memory_list"`
  6. `MemoryDetail` → `"memory_detail/{cardId}"` (Long path arg)
  7. `AddMemory` → `"add_memory?category={category}"` (optional String query arg)
  8. `EditMemory` → `"edit_memory/{cardId}"` (Long path arg)
  9. `ReviewSession` → `"review_session"`
  10. `ReviewResult` → `"review_result"`
  11. `HabitList` → `"habit_list"`
  12. `HabitDetail` → `"habit_detail/{habitId}"` (Long path arg)
  13. `AddHabit` → `"add_habit"`
  14. `NoteList` → `"note_list"`
  15. `NoteDetail` → `"note_detail/{noteId}"` (Long path arg)
  16. `AddNote` → `"add_note"`
  17. `Analytics` → `"analytics"`
  18. `Goals` → `"goals"`
  19. `Settings` → `"settings"`
  20. `Profile` → `"profile"`
  21. `Search` → `"search"`
  22. `Focus` → `"focus"`
  23. `FeynmanTest` → `"feynman_test/{cardId}"` (Long path arg)
  24. `Graph` → `"graph"`
  25. `InterviewHome` → `"interview_home"`
  26. `Planner` → `"planner"`
  27. `Palace` → `"palace"`
- **Bottom Nav Tabs** (5):
  1. Home → `Icons.Filled/Outlined.Home`
  2. Memory → `Icons.Filled/Outlined.Psychology`
  3. Review → `Icons.Filled/Outlined.RateReview` (badge: due card count)
  4. Habits → `Icons.Filled/Outlined.CheckCircle`
  5. Analytics → `Icons.Filled/Outlined.BarChart`
- **Files Created**:
  - `core/navigation/BottomNavItem.kt`
  - `core/ui/components/BottomNavBar.kt`
  - `MainScreen.kt`
- **Files Modified**:
  - `core/navigation/Routes.kt` (expanded from 10 routes to 27)
  - `core/navigation/ReviZenNavGraph.kt` (all 27 destinations with placeholder composables)
  - `MainActivity.kt` (uses MainScreen scaffold, injects UserPreferencesRepository and MemoryCardDao)
  - `PROJECT_CONTEXT.md`


