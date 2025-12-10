plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.compose)
    alias(libs.plugins.conventions.hilt)
    alias(libs.plugins.conventions.hilt.autobind)
}

android {
    namespace = "ru.agrachev.feature.favorites.presentation"
}

dependencies {
    implementation(project(":core-presentation"))
    implementation(project(":feature-favorites-domain"))
    implementation(project(":feature-favorites-navigation"))
    implementation(project(":feature-productcard-navigation"))

    implementation(libs.androidx.compose.material.icons.extended)
}
