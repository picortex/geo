pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven {
            name = "piCortex"
            url = uri("http://65.21.254.230:1050/repository/internal/")
            isAllowInsecureProtocol = true
            credentials {
                username = "admin"
                password = "admin@123"
            }
        }
        mavenLocal()
    }

}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

val tmp = 2

rootProject.name = "asoft"

includeBuild("./kash-generator")
// dependencies
includeSubs("functions", "../functions", "core")
includeSubs("expect", "../expect", "core")
includeSubs("formatter", "../formatter", "core")

includeSubs("kash", "../kash", "currency", "money")

includeBuild("./geo-generator")
includeSubs("geo", ".", "core", "languages", "countries")