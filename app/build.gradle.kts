plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
   // alias(libs.plugins.google.ksp)
    kotlin("kapt") // The kapt plugin

}

android {
    namespace = "com.hussein.composecodelab"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hussein.composecodelab"
        minSdk = 24
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.animation) // or the latest version

    implementation(libs.coil.compose) // Latest stable release
    // or, for GIFs:
    implementation(libs.coil.gif)
    // or, for SVGs:
    implementation(libs.coil.svg)
    // or, if you need all of them
    implementation(libs.coil) // Includes compose, gif and svg

    implementation(libs.androidx.paging.compose) // Or latest version
    implementation(libs.accompanist.swiperefresh) // Or latest version

    // For serialization
    implementation(libs.kotlinx.serialization.json) // Or latest version

    // ForParcelize
    implementation(libs.androidx.ui.v120) // Check if there is new version
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.android.v343) // Replace with your Koin version
    implementation(libs.koin.androidx.compose) // For Compose integration (if using Compose)
    // Kotlin coroutines (if you're using suspend functions in your DAO)
    implementation(libs.kotlinx.coroutines.android)


    // Optional: Lifecycle components for observing LiveData (if needed)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //RoomDB
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    // To use Kotlin annotation processing tool (kapt)
    kapt(libs.androidx.room.compiler)
    // To use Kotlin Symbol Processing (KSP)
    //ksp(libs.androidx.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)

}