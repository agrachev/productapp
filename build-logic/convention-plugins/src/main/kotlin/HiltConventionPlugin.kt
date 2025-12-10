import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.agrachev.build.logic.convention.plugins.getPluginId
import ru.agrachev.build.logic.convention.plugins.isAndroidProject
import ru.agrachev.build.logic.convention.plugins.libs

@Suppress("unused")
class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) =
        with(target) {
            pluginManager.apply(getPluginId("ksp"))
            if (isAndroidProject) {
                pluginManager.apply(getPluginId("hilt-android"))
                dependencies {
                    add(
                        "implementation",
                        libs.findLibrary("hilt-android").get(),
                    )
                    add(
                        "ksp",
                        libs.findLibrary("hilt-android-compiler").get(),
                    )
                }
            } else {
                dependencies {
                    add(
                        "implementation",
                        libs.findLibrary("hilt-core").get(),
                    )
                    add(
                        "ksp",
                        libs.findLibrary("hilt-compiler").get(),
                    )
                }
            }
        }
}
