plugins {
    kotlin("multiplatform") version "1.6.10"
    id("com.android.library") version "7.0.3"
    id("maven-publish")
}

group = "com.doublesymmetry"
version = "0.0.1"

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }

    if (System.getProperty("idea.sync.active") == null) {
        val appleMain = sourceSets.create("appleMain")
        val appleTest = sourceSets.create("appleTest")

        macosX64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        macosArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }

        iosArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        iosX64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        iosSimulatorArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }

        watchosArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        watchosSimulatorArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }

        tvosArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        tvosX64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
        tvosSimulatorArm64().apply {
            compilations.getByName("main").source(appleMain)
            compilations.getByName("test").source(appleTest)
        }
    } else {
        // Using intelliJ, pretend we have a single target with code in apple
        macosX64("apple")
    }

    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
            }
        }
        val androidTest by getting

        val appleMain by getting {
            dependencies {
                implementation("co.touchlab:stately-concurrency:1.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}
