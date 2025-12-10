plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.compose)
    alias(libs.plugins.conventions.hilt)
    alias(libs.plugins.conventions.hilt.autobind)
    id("kotlin-parcelize")
}

android {
    namespace = "ru.agrachev.feature.productlist.presentation"
}

dependencies {
    implementation(project(":core-presentation"))
    implementation(project(":feature-productlist-domain"))
    implementation(project(":feature-productlist-navigation"))
    implementation(project(":feature-productcard-navigation"))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}
