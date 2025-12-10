plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.compose)
}

android {
    namespace = "ru.agrachev.core.presentation"
}

dependencies {
    api(project(":core-domain"))
    api(project(":core-navigation"))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}
