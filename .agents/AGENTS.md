# Agent Instructions

This repository is managed with a permanent project context standard. Every AI agent working on this repository MUST follow these rules strictly to maintain codebase integrity and consistency.

## 1. Context and State Preservation
- **Always read [PROJECT_CONTEXT.md](file:///wsl$/Ubuntu-22.04/home/adimauryaji/KYREXI/Projects/ReviZen/PROJECT_CONTEXT.md) before doing anything.** It serves as the single source of truth for the project state, active features, database configurations, and session records.
- **Keep documentation synchronized with the codebase.** Any changes to architecture, database entities, navigation routes, UI themes, or dependencies must immediately be documented in `PROJECT_CONTEXT.md`.

## 2. Code Quality and Maintenance
- **Never rewrite working code unnecessarily.** Do not engage in unsolicited refactoring of functional code unless explicitly requested by the user or required to resolve bugs/incompatibilities.
- **Preserve architectural consistency.** ReviZen uses a feature-based Clean Architecture with MVVM. Keep core classes in `core/` and feature layers inside `features/<name>/{data,domain,ui}`.
- **Preserve coding conventions.** Adhere strictly to the defined conventions (e.g. Kotlin PascalCase/camelCase, structured error handling with `ReviZenException`, clean Jetpack Compose parameters).
- **Offline Constraint.** ReviZen is 100% offline. No cloud sync, external authentication providers (e.g. Clerk, Firebase), or remote database integrations should be added.

## 3. Communication and Session Records
- **Update the Session History in PROJECT_CONTEXT.md after every completed task.** Append details about the user's request, the work performed, files altered, additions/deletions, decisions made, and problems solved.
