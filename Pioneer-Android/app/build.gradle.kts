import dependencies.TestDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
        versionName = BuildConfig.versionName
        versionCode = BuildConfig.versionCode

        multiDexEnabled = BuildConfig.multiDex

        //指定只支持中文字符
        resConfig("zh-rCN")

        testInstrumentationRunner = BuildConfig.TEST_INSTRUMENTATION_RUNNER

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
                argument("room.incremental", "true")
                argument("room.expandProjection", "true")
            }
        }

    }

    buildTypes {
        getByName(BuildDebug.key) {
            isMinifyEnabled = BuildDebug.minifyEnabled //混淆模式
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

        getByName(BuildRelease.key) {
            isMinifyEnabled = BuildRelease.minifyEnabled //混淆模式
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    implementation(project(BuildModules.libraryService))

    DependenciesEach.modules.forEach {
        implementation(project(it))
    }
    DependenciesEach.processors.forEach {
        kapt(it)
    }

    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.junitAndroid)
    androidTestImplementation(TestDependencies.espresso)

    debugImplementation(TestDependencies.leakcanaryDebug)
    releaseImplementation(TestDependencies.leakcanaryRelease)
}
