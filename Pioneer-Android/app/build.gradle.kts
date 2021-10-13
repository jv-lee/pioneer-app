plugins {
    id(BuildPlugin.application)
    id(BuildPlugin.kotlin)
    id(BuildPlugin.kapt)
    id(BuildPlugin.navigationSafeargs)
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val signingConfigs = signingConfigs.create(BuildType.RELEASE).apply {
        storeFile(File("${project.rootDir}/${BuildRelease.SigningConfig.storeFile}"))
        storePassword(BuildRelease.SigningConfig.storePassword)
        keyAlias(BuildRelease.SigningConfig.keyAlias)
        keyPassword(BuildRelease.SigningConfig.keyPassword)
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildDebug.isMinifyEnabled //混淆模式
            isZipAlignEnabled = BuildDebug.zipAlignEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildRelease.isMinifyEnabled //混淆模式
            isZipAlignEnabled = BuildRelease.zipAlignEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs
        }
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
    appDependencies()
}
