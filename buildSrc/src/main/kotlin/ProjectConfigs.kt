import org.gradle.api.JavaVersion

object ProjectConfigs {
    const val applicationId = "com.yuriisurzhykov.tddgraded"
    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val minSdkVersion = 26
    val jvmSourceCompatibility = JavaVersion.VERSION_17
    val jvmTargetCompatibility = JavaVersion.VERSION_17
}