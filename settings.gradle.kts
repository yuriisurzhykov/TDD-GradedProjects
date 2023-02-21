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
include(":core")
include(":presentation")
include(":palindrome")
include(":stringReverse")
include(":primeNumber")
include(":testingCore")
