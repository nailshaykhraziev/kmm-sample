plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.compose.ui:ui:1.0.4")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.material:material:1.0.4")
    implementation("androidx.compose.foundation:foundation:1.0.4")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.fs.testkmp.android"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}