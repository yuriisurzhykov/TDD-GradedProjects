plugins {
    id("com.android.library")
    id(Plugins.hilt)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "${ProjectConfigs.applicationId}.testing"
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion
        testInstrumentationRunner = "com.yuriisurzhykov.testingcore.android.CustomTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    api(Dependencies.Testing.JUnit4)
    api(Dependencies.Testing.coroutinesTest)
    api(Dependencies.Testing.coroutinesTest)
    api(Dependencies.Testing.androidJUnit4)
    api(Dependencies.Testing.espressoCore)
    api(Dependencies.Testing.hiltTesting)
    api(Dependencies.DI.hilt)
    kaptAndroidTest(Dependencies.DI.hiltCompiler)
}