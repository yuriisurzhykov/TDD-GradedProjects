@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TDD-GradedProjects"
include(":app")
include(":core:data")
include(":core:presentation")
include(":core:testing")
include(":newbie:palindrome")
include(":newbie:stringReverse")
include(":newbie:primeNumber")
include(":newbie:tictactoe")
include(":core:data")
include(":core:domain")
