plugins {
    id("com.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.example.mypokedexcompose"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.room.ktx)
    implementation(libs.androidx.room.common)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.kotlinx.serialization.json)
}
