plugins {
    alias(libs.plugins.conventions.android.library)
    alias(libs.plugins.conventions.hilt)
}

android<Lib> {
    namespace = "ru.agrachev.feature.connectivity"
}
