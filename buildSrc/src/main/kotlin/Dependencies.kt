object Dependencies {

    object Android {
        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.Android.androidCoreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val materialComponents =
            "com.google.android.material:material:${Versions.Android.materialComponents}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Android.fragmentKtx}"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycleKtx}"
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.livedataKtx}"
        const val collectionsKtx = "androidx.collection:collection-ktx:${Versions.Android.collectionsKtx}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.viewmodelKtx}"
    }

    object Testing {
        const val JUnit4 = "junit:junit:${Versions.Testing.JUnit4}"
        const val androidJUnit4 = "androidx.test.ext:junit:${Versions.Testing.androidJUnit4}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Testing.coroutinesTest}"
        const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.DI.hilt}"
    }

    object DI {
        const val hilt = "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.DI.hilt}"
    }
}