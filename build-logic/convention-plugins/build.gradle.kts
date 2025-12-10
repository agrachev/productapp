plugins {
    `kotlin-dsl`
}

group = "ru.agrachev.build.logic.convention.plugins"
version = "unspecified"

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.build.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

kotlin {
    compilerOptions {
        jvmToolchain(libs.versions.javaVersion.map { it.toInt() }.get())
    }
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "ru.agrachev.convention.plugins.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("hilt") {
            id = "ru.agrachev.convention.plugins.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("hiltAutoBind") {
            id = "ru.agrachev.convention.plugins.hilt.autobind"
            implementationClass = "HiltAutoBindConventionPlugin"
        }
        register("androidLibrary") {
            id = "ru.agrachev.convention.plugins.androidLibrary"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "ru.agrachev.convention.plugins.androidApplication"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "ru.agrachev.convention.plugins.kotlinLibrary"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}