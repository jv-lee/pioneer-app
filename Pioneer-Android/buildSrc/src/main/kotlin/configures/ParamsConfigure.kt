package configures

import build.BuildDebug
import build.BuildRelease
import build.BuildTypes
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
fun Project.paramsConfigure() {
    extensions.configure<LibraryExtension> {
        buildTypes {
            getByName(BuildTypes.DEBUG) {
                BuildDebug.paramsMap.onEach {
                    buildConfigField("String", it.key, "\"" + it.value + "\"")
                }
            }
            getByName(BuildTypes.RELEASE) {
                BuildRelease.paramsMap.onEach {
                    buildConfigField("String", it.key, "\"" + it.value + "\"")
                }
            }
        }
    }
}