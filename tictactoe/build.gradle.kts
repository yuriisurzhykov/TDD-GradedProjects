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
    namespace = "${ProjectConfigs.applicationId}.tictactoe"
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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