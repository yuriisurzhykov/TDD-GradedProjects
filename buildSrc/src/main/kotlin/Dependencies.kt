object Dependencies {

    object Android {
        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.Android.androidCoreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val materialComponents =
            "com.google.android.material:material:${Versions.Android.materialComponents}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
    }

    object Testing {
        const val JUnit4 = "junit:junit:${Versions.Testing.JUnit4}"
        const val androidJUnit4 = "androidx.test.ext:junit:${Versions.Testing.androidJUnit4}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
    }

    object DI {
        const val hilt = "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.DI.hilt}"
    }
}