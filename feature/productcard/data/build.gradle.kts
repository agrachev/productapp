plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.hilt)
}

android {
    namespace = "ru.agrachev.feature.productcard.data"
}

dependencies {
    implementation(project(":feature-productcard-domain"))
    implementation(project(":core-data-persistence"))
}
