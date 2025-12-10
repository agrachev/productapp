plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.hilt)
}

android {
    namespace = "ru.agrachev.feature.productlist.data"
}

dependencies {
    implementation(project(":feature-productlist-domain"))
    implementation(project(":core-data-remote"))
    implementation(project(":core-data-persistence"))

    ksp(libs.androidx.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.androidx.work.runtime.ktx)
    androidTestImplementation(libs.androidx.work.testing)
}
