import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.agrachev.build.logic.convention.plugins.getPluginId
import ru.agrachev.build.logic.convention.plugins.libs

@Suppress("unused")
class HiltAutoBindConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) =
        with(target) {
            pluginManager.apply(getPluginId("ksp"))
            dependencies {
                add(
                    "implementation",
                    libs.findLibrary("android").get(),
                )
                add(
                    "implementation",
                    libs.findLibrary("android-api").get(),
                )
                add(
                    "testImplementation",
                    libs.findLibrary("android-testing").get(),
                )
                add(
                    "kspTest",
                    libs.findLibrary("compiler").get(),
                )
                add(
                    "ksp",
                    libs.findLibrary("compiler").get(),
                )
            }
        }
}
