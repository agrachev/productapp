plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.hilt)
}

android {
    namespace = "ru.agrachev.feature.favorites.data"
}

dependencies {
    implementation(project(":feature-favorites-domain"))
    implementation(project(":core-data-persistence"))
}
