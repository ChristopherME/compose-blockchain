object Releases {
    val versionCode = 1
    val versionName = "0.0.1"
}

object DefaultConfig {
    val buildToolsVersion = "30.0.3"
    val appId = "com.christopher_elias.blockchain"
    val minSdk = 21
    val targetSdk = 30
    val compileSdk = 30
}

object Core {
    val network = ":core:network"
    val navigation = ":core:navigation"
}

object Versions {
    const val KOTLIN_VERSION = "1.5.10"
    const val COROUTINES_VERSION = "1.5.0"
    const val GRADLE_TOOLS_VERSION = "7.0.0-beta03"

    const val RETROFIT_VERSION = "2.9.0"
    const val LOGGING_INTERCEPTOR_VERSION = "4.9.0"
    const val MOCK_WEB_SERVER_VERSION = "4.9.0"

    const val TIMBER_VERSION = "4.7.1"

    const val TEST_MOCKK_VERSION = "1.11.0"
    const val TEST_JUNIT_VERSION = "4.13.2"

    const val ANDROIDX_CORE_VERSION = "1.5.0"
    const val ANDROIDX_APP_COMPAT_VERSION = "1.3.0"
    const val GOOGLE_MATERIAL_VERSION = "1.3.0"
    const val ANDROIDX_LIFECYCLE_VERSION = "2.3.1"
    const val COMPOSE_VERSION = "1.0.0-beta08"

    const val DAGGER_HILT_VERSION = "2.36"
}

object Libs {

    val androidToolsBuildGradle = "com.android.tools.build:gradle:7.0.0-beta03"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10"
    val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:2.36"

    val androidXCore = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
    val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APP_COMPAT_VERSION}"
    val googleMaterial = "com.google.android.material:material:${Versions.GOOGLE_MATERIAL_VERSION}"

    // ViewModel
    val androidXViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"

    // Compose
    val composeUi = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    val composeRuntimeLiveData =
        "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE_VERSION}"
    val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    val composeActivity = "androidx.activity:activity-compose:1.3.0-beta01"


    // Kotlin Coroutines
    val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
    val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}"

    // Timber
    val timber = "com.jakewharton.timber:timber:${Versions.TIMBER_VERSION}"

    // Square
    val squareRetrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    val squareMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    val squareLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR_VERSION}"
    val squareMockWerbServerTest =
        "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEB_SERVER_VERSION}"

    // DaggerHilt
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.DAGGER_HILT_VERSION}"
    val daggerHiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.DAGGER_HILT_VERSION}"

    // Mockk
    val mockkTesting = "io.mockk:mockk:${Versions.TEST_MOCKK_VERSION}"
    val jUnit4Testing = "junit:junit:${Versions.TEST_JUNIT_VERSION}"

    // AndroidTest
    val jUnitUiTesting = "androidx.test.ext:junit:1.1.2"
    val espressoUiTesting = "androidx.test.espresso:espresso-core:3.3.0"
    val composeUiTesting = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
}