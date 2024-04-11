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
    id(Plugins.kotlinAndroid)
}

android {
    namespace = "${ProjectConfigs.applicationId}.presentation"
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
    implementation(Dependencies.Android.androidCoreKtx)
    implementation(Dependencies.Android.materialComponents)

    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    api(composeBom)
    api("androidx.compose.material3:material3")
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.activity:activity-compose:1.8.1")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    testImplementation(Dependencies.Testing.JUnit4)
    androidTestImplementation(Dependencies.Testing.androidJUnit4)
    androidTestImplementation(Dependencies.Testing.espressoCore)
}