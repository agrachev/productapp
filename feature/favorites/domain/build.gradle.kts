plugins {
    alias(libs.plugins.conventions.kotlin.library)
}

dependencies {
    api(project(":core-domain"))

    api(libs.androidx.room.paging)
}
