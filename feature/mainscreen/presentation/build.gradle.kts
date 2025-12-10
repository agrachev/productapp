plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.compose)
    alias(libs.plugins.conventions.hilt)
    alias(libs.plugins.conventions.hilt.autobind)
}

android {
    namespace = "ru.agrachev.feature.mainscreen.presentation"
}

dependencies {
    implementation(project(":core-presentation"))
    implementation(project(":feature-mainscreen-navigation"))
    implementation(project(":feature-connectivity"))
}