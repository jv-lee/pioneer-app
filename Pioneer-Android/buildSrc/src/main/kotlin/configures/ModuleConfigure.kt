package configures

import build.BuildConfig
import build.BuildPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kapt
import commonProcessors
import baseService
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

/**
 * @author jv.lee
 * @data 2021/10/1
 * @description 组件模块配置依赖扩展
 */
@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
fun Project.moduleConfigure(
    projectConfigure: Project.() -> Unit = {},
    androidConfigure: LibraryExtension.() -> Unit = {}
) {
    plugins.apply(BuildPlugin.library)
    plugins.apply(BuildPlugin.kotlin)
    plugins.apply(BuildPlugin.kapt)

    projectConfigure()

    extensions.configure<LibraryExtension> {
        compileSdk = BuildConfig.compileSdk

        defaultConfig {
            minSdk = BuildConfig.minSdk
            targetSdk = BuildConfig.targetSdk
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
            kotlinOptions.freeCompilerArgs = kotlinOptions.freeCompilerArgs.toMutableList().also {
                it.add("-Xallow-jvm-ir-dependencies")
                it.add("-Xskip-prerelease-check")
                it.add("-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi")
                it.add("-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi")
                it.add("-Xopt-in=androidx.compose.material.ExperimentalMaterialApi")
                it.add("-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi")
                it.add("-Xopt-in=kotlin.RequiresOptIn")
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            dataBinding = true
            viewBinding = true
        }

        kapt {
            generateStubs = true
        }

        androidConfigure()
    }

    dependencies {
        commonProcessors()
        baseService()
    }
}