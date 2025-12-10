@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Product App"

includeProjects(
    "app",
    "core/domain",
    "core/data/persistence",
    "core/data/remote",
    "core/presentation",
    "core/navigation",

    "feature/connectivity",

    "feature/productlist/domain",
    "feature/productlist/data",
    "feature/productlist/presentation",
    "feature/productlist/navigation",

    "feature/favorites/domain",
    "feature/favorites/data",
    "feature/favorites/presentation",
    "feature/favorites/navigation",

    "feature/productcard/domain",
    "feature/productcard/data",
    "feature/productcard/presentation",
    "feature/productcard/navigation",

    "feature/mainscreen/presentation",
    "feature/mainscreen/navigation",
)

private fun includeProjects(vararg paths: String) {
    paths.forEach { path ->
        val projectName = ":${path.replace('/', '-')}"
        include(projectName)
        project(projectName).projectDir = file(path)
    }
}
