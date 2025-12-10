plugins {
    alias(libs.plugins.conventions.kotlin.library)
}

dependencies {
    api(project(":core-domain"))
}
