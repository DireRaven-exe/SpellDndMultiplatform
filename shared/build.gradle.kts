import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("io.github.skeptick.libres")
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)
    alias(libs.plugins.sqlDelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
                api(compose.materialIconsExtended)
                implementation(libs.kotlinX.coroutines)

                api(libs.ktor.core)
                implementation(libs.ktor.contentNegotiation)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)

                api(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.kotlinX.serializationJson)

                implementation(libs.kotlinX.dateTime)

                implementation(libs.multiplatformSettings.noArg)
                implementation(libs.multiplatformSettings.coroutines)
                api(libs.resources)
                api(libs.resources.compose)

                api(libs.napier)

                implementation(libs.imageLoader)

                api(libs.preCompose)
                api(libs.preCompose.viewmodel)

                implementation(libs.sqlDelight.coroutine)

                implementation(libs.libres.compose)

                sourceSets["androidMain"].dependencies {
                    implementation(libs.ktor.android)
                    implementation(libs.sqlDelight.android)
                }

                sourceSets["desktopMain"].dependencies {
                    implementation(libs.sqlDelight.jvm)
                }

                sourceSets["iosMain"].dependencies {
                    implementation(libs.ktor.darwin)
                    implementation(libs.sqlDelight.native)
                }

            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.androidX.core)
                implementation(libs.ktor.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.sqlDelight.jvm)
            }
        }
    }
}

android {
    compileSdk = 34
    namespace = "com.spelldnd.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

libres {
    generatedClassName = "MainRes" // "Res" by default
    generateNamedArguments = true // false by default
    baseLocaleLanguageCode = "ru" // "en" by default
    camelCaseNamesForAppleFramework = false // false by default
}

buildkonfig {
    packageName = "com.spelldnd.common"

    defaultConfigs {
    }
}

sqldelight {
    databases {
        create("SpellDndDatabase") {
            packageName.set("com.spelldnd.shared.data.cashe.sqldelight")
            srcDirs.setFrom("src/commonMain/kotlin")
        }
    }
}