plugins {
    alias(libs.plugins.conventions.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.conventions.hilt)
}

android {
    namespace = "ru.agrachev.productapp"

    defaultConfig {
        applicationId = "ru.agrachev.productapp"
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core-presentation"))
    implementation(project(":feature-mainscreen-presentation"))

    runtimeOnly(project(":feature-productlist-data"))
    runtimeOnly(project(":feature-productlist-presentation"))
    runtimeOnly(project(":feature-favorites-data"))
    runtimeOnly(project(":feature-favorites-presentation"))
    runtimeOnly(project(":feature-productcard-data"))
    runtimeOnly(project(":feature-productcard-presentation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.hilt.work)
}
