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
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "${ProjectConfigs.applicationId}.primenumber"
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion

        testInstrumentationRunner = "${ProjectConfigs.applicationId}.primenumber.CustomTestRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":core"))
    api(project(":presentation"))
    api(Dependencies.Android.androidCoreKtx)
    api(Dependencies.Android.materialComponents)
    api(Dependencies.DI.hilt)
    api(Dependencies.Android.viewmodelKtx)
    api(Dependencies.Android.fragmentKtx)
    kapt(Dependencies.DI.hiltCompiler)
    testImplementation(Dependencies.Testing.JUnit4)
    testImplementation(Dependencies.Testing.coroutinesTest)
    testImplementation(project(":testingCore"))
    androidTestImplementation(project(":testingCore"))
    androidTestImplementation(Dependencies.Testing.androidJUnit4)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.hiltTesting)
    androidTestImplementation(Dependencies.Testing.coroutinesTest)
    kaptAndroidTest(Dependencies.DI.hiltCompiler)
}