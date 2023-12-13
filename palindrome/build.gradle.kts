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
    namespace = "${ProjectConfigs.applicationId}.palindrome"
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        minSdk = ProjectConfigs.minSdkVersion
        testApplicationId = "${ProjectConfigs.applicationId}.palindrome"
        testInstrumentationRunner = "${ProjectConfigs.applicationId}.palindrome.CustomTestRunner"
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
    implementation(project(":presentation"))
    implementation(project(":core"))

    implementation(Dependencies.Android.androidCoreKtx)
    implementation(Dependencies.Android.materialComponents)
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.Android.viewmodelKtx)
    implementation(Dependencies.Android.fragmentKtx)
    kapt(Dependencies.DI.hiltCompiler)
    testImplementation(Dependencies.Testing.JUnit4)
    testImplementation(Dependencies.Testing.coroutinesTest)
    androidTestImplementation(Dependencies.Testing.androidJUnit4)
    androidTestImplementation(Dependencies.Testing.espressoCore)
    androidTestImplementation(Dependencies.Testing.hiltTesting)
    kaptAndroidTest(Dependencies.DI.hiltCompiler)
}