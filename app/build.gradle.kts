plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
    namespace = "com.example.mvvmdemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.mvvmdemo"
        minSdk = 24
        targetSdk = 36
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
       // viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.room.runtime)
    ksp("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.glide) // Use the latest stable version
    kapt(libs.glide)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}