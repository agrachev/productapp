plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.hilt)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "ru.agrachev.core.data.persistence"
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":core-domain"))

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    api(libs.androidx.room.paging)
}
