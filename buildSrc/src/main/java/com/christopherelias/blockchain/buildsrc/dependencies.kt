package com.christopherelias.blockchain.buildsrc

object Releases {
    const val versionCode = 1
    const val versionName = "0.0.1"
}

object DefaultConfig {
    const val buildToolsVersion = "30.0.3"
    const val appId = "com.christopher_elias.blockchain"
    const val minSdk = 21
    const val targetSdk = 30
    const val compileSdk = 30
}

object Core {
    const val network = ":core:network"
    const val functionalProgramming = ":core:functional-programming"
}

object Modules {
    const val utils = ":utils"
}

object Libs {

    const val androidToolsBuildGradle = "com.android.tools.build:gradle:7.0.0-rc01"

    const val googleMaterial = "com.google.android.material:material:1.4.0"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    //--- Unit Test ---
    const val mockkTesting = "io.mockk:mockk:1.11.0"
    const val jUnit4Testing = "junit:junit:4.13.2"
    //---

    object Kotlin {
        private const val version = "1.5.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Coroutines {
        private const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        // Unit Test
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"

        object Lifecycle {
            private const val version = "2.3.1"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Compose {
            const val version = "1.0.0-rc02"
            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val activity = "androidx.activity:activity-compose:$version"
            object Test {
                //androidTestImplementation
                const val junit = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object Test {
            object Ext {
                //androidTestImplementation
                const val junit = "androidx.test.ext:junit:1.1.2"
            }
            //androidTestImplementation
            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Square {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val moshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
         object Test {
             const val mockWerbServer = "com.squareup.okhttp3:mockwebserver:4.9.0"
         }

    }

    object Hilt {
        private const val version = "2.36"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val kapt = "com.google.dagger:hilt-android-compiler:$version"
    }
}