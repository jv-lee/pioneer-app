import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun Project.kapt(configure: Action<org.jetbrains.kotlin.gradle.plugin.KaptExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kapt", configure)

fun Project.androidConfigure(configure: Action<LibraryExtension>) {
    extensions.configure("android", configure)
}

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
fun Project.moduleConfigure() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")

    extensions.configure<LibraryExtension> {
        compileSdk = BuildConfig.compileSdk

        defaultConfig {
            minSdk = BuildConfig.minSdk
            targetSdk = BuildConfig.targetSdk
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

        buildFeatures {
            dataBinding = true
            viewBinding = true
        }

        kapt {
            generateStubs = true
        }
    }

    dependencies {
        implementation(project(BuildModules.Library.service))
        DependenciesEach.processors.forEach { kapt(it) }
    }
}

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
fun Project.libraryConfigure() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")

    extensions.configure<LibraryExtension> {
        compileSdk = BuildConfig.compileSdk

        defaultConfig {
            minSdk = BuildConfig.minSdk
            targetSdk = BuildConfig.targetSdk
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

        buildFeatures {
            dataBinding = true
            viewBinding = true
        }

        sourceSets {
            getByName("main") {
                assets {
                    srcDir("src/main/assets")
                }
            }
        }

        kapt {
            generateStubs = true
        }
    }

}