// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android Gradle Plugin 8.7.3 (Fixes R8 metadata issues with newer Kotlin)
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false

    // Kotlin 2.0.0 (Modern standard, aligns with your Compose Compiler usage)
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    
    // KSP (Must match Kotlin version exactly: 2.0.0 -> 2.0.0-1.0.21)
    id("com.google.devtools.ksp") version "2.0.0-1.0.21" apply false

    // Hilt (Explicit version to fix "not found" errors)
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}