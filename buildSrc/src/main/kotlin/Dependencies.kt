/*
 * Copyright (c) 2023-2024 Yurii Surzhykov.
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

@file:Suppress("unused")
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

object Dependencies {

    object Android {
        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.Android.androidCoreKtx}"
        const val materialComponents =
            "com.google.android.material:material:${Versions.Android.materialComponents}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Android.fragmentKtx}"
        const val lifecycleKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycleKtx}"
        const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.lifecycleKtx}"
        const val collectionsKtx =
            "androidx.collection:collection-ktx:${Versions.Android.collectionsKtx}"
        const val viewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycleKtx}"
    }

    object Testing {
        const val JUnit4 = "junit:junit:${Versions.Testing.JUnit4}"
        const val androidJUnit4 = "androidx.test.ext:junit:${Versions.Testing.androidJUnit4}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Testing.coroutinesTest}"
        const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.DI.hilt}"
    }

    object DI {
        const val hilt = "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.DI.hilt}"
    }

    object Compose {
        const val compilerVersion = "1.5.6"
        const val material3 = "androidx.compose.material3:material3:${Versions.Compose.material3}"
        const val runtimeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.Compose.runtimeLifecycle}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    }
}