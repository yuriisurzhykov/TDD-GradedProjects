// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id(Plugins.kotlinAndroid) version "1.9.21" apply false
    id(Plugins.hilt) version Versions.DI.hilt apply false
}