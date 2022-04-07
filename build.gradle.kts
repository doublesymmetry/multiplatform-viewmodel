plugins {
    kotlin("multiplatform") version "1.6.10"
    id("com.android.library") version "7.0.3"
    id("convention.publication")
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
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
    }

    macosX64()
    iosArm32()
    iosArm64()
    iosX64()
    watchosArm32()
    watchosArm64()
    watchosX86()
    watchosX64()
    tvosArm64()
    tvosX64()

    macosArm64()
    iosSimulatorArm64()
    watchosSimulatorArm64()
    tvosSimulatorArm64()

    val nativeTargets = listOf(
        "macosX64",
        "iosArm32",
        "iosArm64",
        "iosX64",
        "watchosArm32",
        "watchosArm64",
        "watchosX86",
        "watchosX64",
        "tvosArm64",
        "tvosX64",

        "macosArm64",
        "iosSimulatorArm64",
        "watchosSimulatorArm64",
        "tvosSimulatorArm64"
    )

    
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
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
            }
        }
        val androidTest by getting

        val appleMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("co.touchlab:stately-concurrency:1.2.1")
            }
        }

        val appleTest by creating {
            dependsOn(commonTest)
        }

        with(nativeTargets) {
            map { "${it}Main" }.forEach { getByName(it).dependsOn(appleMain) }
            map { "${it}Test" }.forEach { getByName(it).dependsOn(appleTest) }
        }

        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
            }
        }
    }
}
