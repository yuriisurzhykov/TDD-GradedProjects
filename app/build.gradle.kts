/*
 * Copyright (c) 2023 Yurii Surzhykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = ProjectConfigs.applicationId
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        applicationId = ProjectConfigs.applicationId
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val properties = Properties()
            FileInputStream(file("signing.properties")).use { stream ->
                properties.load(stream)
            }
            storeFile = file(properties.getProperty("keystoreFile"))
            storePassword = properties.getProperty("keystorePassword").toString()
            keyAlias = properties.getProperty("keyAlias").toString()
            keyPassword = properties.getProperty("keyPassword").toString()
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = ProjectConfigs.jvmSourceCompatibility
        targetCompatibility = ProjectConfigs.jvmTargetCompatibility
    }
    kotlinOptions {
        jvmTarget = ProjectConfigs.jvmTarget
    }
}

dependencies {
    implementation(project(":palindrome"))
    implementation(project(":stringReverse"))
    implementation(project(":core"))
    implementation(project(":primeNumber"))
    implementation(project(":fibonacci"))

    implementation(Dependencies.Android.androidCoreKtx)
    implementation(Dependencies.Android.materialComponents)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)
    testImplementation(Dependencies.Testing.JUnit4)
    androidTestImplementation(Dependencies.Testing.androidJUnit4)
    androidTestImplementation(Dependencies.Testing.espressoCore)
}