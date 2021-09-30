/**
 * @author jv.lee
 * @data 2021/9/28
 * @description
 */
object BuildConfig {
    const val applicationId = "com.lee.pioneer"

    const val compileSdk = 30
    const val minSdk = 19
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val multiDex = true

    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildDebug {
    const val minifyEnabled = false
    const val zipAlignEnabled = false
    const val baseApi = "https://gank.io/api/v2/"
}

object BuildRelease {
    const val minifyEnabled = true
    const val zipAlignEnabled = true
    const val baseApi = "https://gank.io/api/v2/"
}

object SigningConfig {
    const val storeFile = "../pioneer.jks"
    const val storePassword = "123456"
    const val keyAlias = "pioneer"
    const val keyPassword = "123456"
}



