/*
 * MIT License
 *
 * Copyright (c) 2021 Andrew Chelimo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinParcelizePlugin)
    id(BuildPlugins.ktlintPlugin)
    id(BuildPlugins.jacocoAndroid)

    id(BuildPlugins.googleServices)
    id(BuildPlugins.firebaseCrashlytics)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.hilt)
}

jacoco {
    toolVersion = Versions.jacoco
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    buildToolsVersion(AndroidSdk.buildToolsVersion)

    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    defaultConfig {
        applicationId = "com.octagon_technologies.smart_calculator"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments = arguments + mapOf("room.incremental" to "true")
//                arguments["room.schemaLocation"] += "$projectDir/schemas"
//            }
//        }
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xallow-result-return-type")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    injectAndroidCore()
    injectViewModel()
    injectCoroutines()
    injectRoom()
    injectHilt()
    injectCommonJVMTest()
    injectCommonAndroidTest()

    implementation(Libraries.glide)
    kapt(KaptLibraries.glide)

    implementation(platform(Libraries.firebaseBOM))
    implementation(Libraries.firebaseCrashlytics)

    implementation(Libraries.groupie)
    implementation(Libraries.groupieDataBinding)
    
    implementation(Libraries.dexter)
    implementation(Libraries.dataStore)
    implementation(Libraries.mathLibrary)
    implementation(Libraries.coordinatorLayout)
}