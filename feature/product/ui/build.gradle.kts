plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("jacoco")
}
jacoco {
    toolVersion = "0.8.8"
}
android {
    namespace = "com.feature.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    kotlinOptions { jvmTarget = "1.8" }
    tasks.withType<Test> { useJUnitPlatform() }
}
tasks.register("jacocoUnitTestReport", JacocoReport::class.java) {
    group = "Reporting"
    description = "Generate overall Jacoco coverage report for the debug build."

    reports {
        html.required.set(true)
        xml.required.set(true)
        xml.required.set(false)
    }

    val buildDir = layout.buildDirectory.asFile.get()
    val javaClasses = fileTree("${buildDir}/intermediates/javac/debug/classes")
    val kotlinClassesUi = fileTree("${buildDir}/tmp/kotlin-classes/debug")
    val kotlinClassesDomain = fileTree("$buildDir/classes/kotlin/main")
    classDirectories.from(files(javaClasses, kotlinClassesUi, kotlinClassesDomain))
    sourceDirectories.from(files("$projectDir/src/main/kotlin", "$projectDir/src/main/java"))
    additionalSourceDirs.from(files("$projectDir/src/main/kotlin", "$projectDir/src/main/java"))
    executionData.setFrom(fileTree(buildDir) {
        include("jacoco/*.exec", "outputs/unit_test_code_coverage/debugUnitTest/*.exec")
    })
}
dependencies {

    implementation(project(":feature:product:domain"))
    implementation(project(":feature:offer:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))

    //Dagger
    implementation(libs.hilt.android)
    debugImplementation(libs.androidx.ui.tooling)

    ksp(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    //Compose
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.paging.compose)
    implementation(libs.material3)

    //Test
    testImplementation( libs.junit.jupiter.api)
    testImplementation( libs.junit.jupiter.engine)
    testImplementation( libs.kotlinx.coroutines.test)
    testImplementation( libs.mockk)
    testImplementation( libs.turbine)

}