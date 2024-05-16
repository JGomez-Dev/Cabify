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
    namespace = "com.core.common"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
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

    //UI
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.paging.compose)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.coil)

    implementation(libs.material3)
    implementation(libs.androidx.material)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Dagger
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Test
    implementation( libs.junit.jupiter.api)
    implementation( libs.junit.jupiter.engine)
    implementation( libs.kotlinx.coroutines.test)
    implementation( libs.mockk)
    implementation( libs.turbine)


}