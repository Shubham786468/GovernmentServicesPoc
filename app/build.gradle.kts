import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.example.governmentservicepoc"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    val localProperties = Properties()
    localProperties.load(rootProject.file("local.properties").inputStream())

    val geminiApiKey = localProperties.getProperty(
        "GEMINI_API_KEY"
    )

    defaultConfig {
        applicationId = "com.example.governmentservicepoc"
        minSdk = 36
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "GEMINI_API_KEY",
            "\"$geminiApiKey\""
        )
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += mutableSetOf("META-INF/INDEX.LIST", "META-INF/DEPENDENCIES")
        }
    }

}

// Enable App Functions aggregation for KSP for make bundle of all scripts in the app.
// bundle into one central schema.
// This is required for App Functions to work properly.
ksp {
    arg("appfunctions:aggregateAppFunctions", "true")
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Compose Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //lifecycle ktx
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.11.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // okHttp
    implementation("com.squareup.okhttp3:okhttp:5.4.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Core WorkManager
    implementation("androidx.work:work-runtime-ktx:2.11.2")

    // If using Hilt to inject your Workers:
    implementation("androidx.hilt:hilt-work:1.4.0")
    ksp("androidx.hilt:hilt-compiler:1.4.0")

    // Gemini
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    // App Functions
    implementation("androidx.appfunctions:appfunctions:1.0.0-alpha08")
    implementation("androidx.appfunctions:appfunctions-service:1.0.0-alpha08")
    ksp("androidx.appfunctions:appfunctions-compiler:1.0.0-alpha08")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")

    //Google ADK
    implementation("com.google.adk:google-adk-kotlin-core-android:0.5.0")
    ksp("com.google.adk:google-adk-kotlin-processor:0.5.0")

    //extended icons
    implementation("androidx.compose.material:material-icons-extended")

    //naviagtion
    implementation("androidx.navigation:navigation-compose:2.9.0")

    //EncryptedSharedPreferences
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

}