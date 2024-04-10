plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = ProjectConfigs.jvmSourceCompatibility
    targetCompatibility = ProjectConfigs.jvmTargetCompatibility
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.javax.inject)
    testImplementation(libs.kotlin.coroutines.test)
}