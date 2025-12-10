plugins {
    alias(libs.plugins.conventions.kotlin.library)
    alias(libs.plugins.conventions.hilt)
}

dependencies {
    api(project(":core-domain"))

    api(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.annotation)
}
