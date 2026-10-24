pluginManagement {
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            name = "GitHubPackages"
            setUrl("https://maven.pkg.github.com/khkim6040/demo-b-platform")
            credentials {
                username = providers.gradleProperty("gpr.user")
                    .orElse(providers.environmentVariable("GITHUB_ACTOR")).orNull
                password = providers.gradleProperty("gpr.key")
                    .orElse(providers.environmentVariable("GITHUB_TOKEN")).orNull
            }
            content { includeGroup("io.github.khkim6040.platform") }
        }
    }
    versionCatalogs {
        create("libs") {
            from("io.github.khkim6040.platform:platform-catalog:${providers.gradleProperty("platformVersion").get()}")
        }
    }
}

rootProject.name = "demo-b-template"

include(
    "libs:domain",
    "apps:api",
    "apps:admin",
    "apps:consumer",
    "apps:batch",
)
