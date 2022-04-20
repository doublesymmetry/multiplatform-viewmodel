plugins {
    kotlin("multiplatform") version "1.6.10"
    id("com.android.library") version "7.0.3"
    id("convention.publication")
    id("binary-compatibility-validator") version "0.8.0"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

group = "com.doublesymmetry"
version = "0.0.2"

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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
            }
        }
        val androidTest by getting

        val darwinMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.2")
            }
        }

        val darwinTest by creating {
            dependsOn(commonTest)
        }

        with(nativeTargets) {
            map { "${it}Main" }.forEach { getByName(it).dependsOn(darwinMain) }
            map { "${it}Test" }.forEach { getByName(it).dependsOn(darwinTest) }
        }

        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }
}
