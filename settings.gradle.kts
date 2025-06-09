pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "Pokedex"

include(":app")
include(":core")
include(":core-ui")
include(":core-network")
include(":local-storage")
include(":common:domain")
include(":di-loader:pokemons:api")
include(":di-loader:pokemons:impl")
include(":features:pokemons:data")
include(":features:pokemons:domain")
include(":features:pokemons:presentation")