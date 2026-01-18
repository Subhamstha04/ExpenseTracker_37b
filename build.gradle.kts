// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // These aliases come from your gradle/libs.versions.toml
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Add the Google Services plugin here (Firebase)
    id("com.google.gms.google-services") version "4.4.4" apply false
}

// Keep the clean task to allow clearing the build folder
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}