pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CabifyTest"
include(":app")
include(":core:network")
include(":core:common")
include(":core:navigation")
include(":core:conventions")
include(":feature:product:ui")
include(":feature:product:domain")
include(":feature:product:data")
include(":feature:offer:domain")
include(":feature:offer:data")
include(":feature:onboarding:ui")
