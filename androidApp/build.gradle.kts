plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion = "1.1.0"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.navigation:navigation-compose:2.4.1")

    implementation("com.google.android.material:material:1.5.0")

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation( "com.google.android.exoplayer:exoplayer:2.17.0")

    implementation("io.insert-koin:koin-android:3.1.5")
    implementation("io.insert-koin:koin-androidx-compose:3.1.5")
}
