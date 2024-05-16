plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("jacoco")
}
jacoco {
    toolVersion = "0.8.8"
}
android {
    namespace = "com.core.navigation"
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.androidx.hilt.navigation.compose)
}