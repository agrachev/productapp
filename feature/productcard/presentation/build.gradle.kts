plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.compose)
    alias(libs.plugins.conventions.hilt)
    alias(libs.plugins.conventions.hilt.autobind)
}

android {
    namespace = "ru.agrachev.feature.productcard.presentation"
}

dependencies {
    implementation(project(":core-presentation"))
    implementation(project(":feature-productcard-domain"))
    implementation(project(":feature-productcard-navigation"))

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
