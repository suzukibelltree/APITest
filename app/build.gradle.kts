plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.googleDevToolsKsp)
    kotlin("plugin.serialization") version "1.9.20"
}

android {
    namespace = "com.example.apitest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apitest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    // Ktor
    // Ktor Client のコアライブラリ
    implementation("io.ktor:ktor-client-core:2.3.0")

    // Android 用の Ktor Client（環境に応じて適切なものを選択）
    implementation("io.ktor:ktor-client-android:2.3.0")

    // JSONのシリアライズ・デシリアライズ（kotlinx.serialization）
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")


    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    // Coilの基本ライブラリ
    implementation("io.coil-kt:coil:2.6.0")

    // Jetpack Compose を使う場合（Compose対応版）
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha05")

    // Room
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1") // For Kotlin extensions
    implementation("androidx.room:room-paging:2.7.1") // For Paging support
    ksp("androidx.room:room-compiler:2.7.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}