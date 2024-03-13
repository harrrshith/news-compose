import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.harshith.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.harshith.news"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }


    }
    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    localProperties.load(FileInputStream(localPropertiesFile))

    buildTypes {
        getByName("debug"){
            buildConfigField("String", "API_KEY", localProperties.getProperty("NEWS_API"))
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
     packaging.resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
     }
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(platform(libs.androidx.kotlin.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose.ui)
    implementation(libs.androidx.material3)
    testImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.viemodel.compose)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.square.retrofit2)
    implementation(libs.retrofit.convertor.gson)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.coil.compose)
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.compose.shimmer)
}