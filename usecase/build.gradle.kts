plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    
    implementation(project(":domain"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroitunes.test)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(libs.junit)
    implementation(libs.mockito.kotlin)
}