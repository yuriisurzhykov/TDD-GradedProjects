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

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "${ProjectConfigs.applicationId}.testing"
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        minSdk = ProjectConfigs.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    api(project(":core"))
    api(project(":presentation"))
    api(Dependencies.Android.androidCoreKtx)
    api(Dependencies.Android.materialComponents)
    api(Dependencies.Testing.JUnit4)
    api(Dependencies.Testing.coroutinesTest)
    api(Dependencies.Testing.coroutinesTest)
    api(Dependencies.Testing.androidJUnit4)
    api(Dependencies.Testing.espressoCore)
}