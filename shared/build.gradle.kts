import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    val ktorVersion = "1.6.7"
    val sqlDelight = "1.5.2"

    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

                implementation("io.insert-koin:koin-core:3.1.5")
                implementation("com.squareup.sqldelight:runtime:$sqlDelight")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelight")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelight")
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
}

sqldelight {
    database("AppDatabase") {
        // ???????????? ???????? ?? ???????????????? ?? ?????????????????????? ??????????????
        // ?????????????????? ?? build.generated.code.AppDatabase.com.fs.testkmp, ?????? ?? ?????????? ???????? ????
        packageName = "com.fs.testkmp.database"
        // ???????? ?????????? ???????????? ???????????? ?? shared ?????????????? ?? kotlin.
        // ?? ?????? ???????????? ???????? ?????? ???????????? com.fs.testkmp, ?????? ?????????????????? sql ??????????
        sourceFolders = listOf("sqldelight")
    }
}
