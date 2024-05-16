plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("jacoco")
}
jacoco {
    toolVersion = "0.8.8"
}
android {
    namespace = "com.jgomez.cabifytest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jgomez.cabifytest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
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

    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:conventions"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:product:data"))
    implementation(project(":feature:product:domain"))
    implementation(project(":feature:product:ui"))
    implementation(project(":feature:offer:data"))
    implementation(project(":feature:offer:domain"))
    implementation(project(":feature:onboarding:ui"))

    //Dagger
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.core.splashscreen)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation("androidx.compose.ui:ui-test-junit4:1.6.7")

}