// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.christopherelias.blockchain.buildsrc.Libs.androidToolsBuildGradle)
        //classpath("com.android.tools.build:gradle:7.0.0-rc01")
        classpath(com.christopherelias.blockchain.buildsrc.Libs.Kotlin.gradlePlugin)
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath(com.christopherelias.blockchain.buildsrc.Libs.Hilt.gradlePlugin)
        //classpath("com.google.dagger:hilt-android-gradle-plugin:2.36")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}