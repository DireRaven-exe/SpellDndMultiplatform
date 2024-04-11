plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.firebase.appDistribution.plugin) apply false
    alias(libs.plugins.firebase.crashlytics.plugin) apply false
    alias(libs.plugins.firebase.performance.plugin) apply false

    alias(libs.plugins.ktLint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.gradleVersionUpdates)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            enableExperimentalRules.set(true)
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
            include("**/kotlin/**")
        }
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

tasks.register("clean").configure {
    delete("build") // dependencyUpdates task, for example, writes here
}

buildscript {
    dependencies {
        classpath("io.github.skeptick.libres:gradle-plugin:1.2.2")
    }
}

