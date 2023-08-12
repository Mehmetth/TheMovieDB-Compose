plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.mehmetpetek.themoviedbcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mehmetpetek.themoviedbcompose"
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
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    productFlavors {
        create("google") {
            dimension = "platform"
        }
        create("live") {
            dimension = "env"
            applicationId = "com.mehmetpetek.themoviedb"
            resValue("string", "app_name", "The Movie DB")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_TOKEN",
                "YOUR_API_KEY_HERE"
            )
            buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
        }
        create("dev") {
            dimension = "env"
            applicationId = "com.mehmetpetek.themoviedb.beta"
            resValue("string", "app_name", "The Movie DB - BETA")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_TOKEN",
                "YOUR_API_KEY_HERE"
            )
            buildConfigField("String", "IMAGE_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
        }
    }
    flavorDimensions += listOf("platform", "env")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    implementation(libs.navigation)
    implementation(libs.navigation.fragment)
    implementation(libs.coil)

    //Async Operations
    implementation(libs.coroutines)

    //DI
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    //HTTP Request
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //HTTP Request Realtime Viewer
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //Exoplayer
    implementation(libs.exoplayer)

    //RoomDB - Offline Cache
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}