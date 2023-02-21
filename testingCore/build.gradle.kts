plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "${ProjectConfigs.applicationId}.testing"
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion

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
    api(Dependencies.Android.appCompat)
    api(Dependencies.Android.materialComponents)
    testImplementation(Dependencies.Testing.JUnit4)
    testImplementation(Dependencies.Testing.coroutinesTest)
    androidTestImplementation(Dependencies.Testing.coroutinesTest)
    androidTestImplementation(Dependencies.Testing.androidJUnit4)
    androidTestImplementation(Dependencies.Testing.espressoCore)
}