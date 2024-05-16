// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    alias(libs.plugins.androidLibrary) apply false
}

apply(plugin = "jacoco")
tasks.register<JacocoReport>("jacocoFullReport") {
    group = "reporting"
    description = "Coverage all modules"

    val jacocoModules = subprojects
        .filter { it.tasks.findByName("jacocoUnitTestReport") != null }
        .map { it.tasks.findByName("jacocoUnitTestReport") as JacocoReport }

    dependsOn(jacocoModules)

    additionalSourceDirs.from(jacocoModules.map { it.additionalSourceDirs })
    sourceDirectories.from(jacocoModules.map { it.sourceDirectories })
    classDirectories.from(jacocoModules.map { it.classDirectories })
    executionData.from(jacocoModules.map { it.executionData })

    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }
}