pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf("kommander", "kollections", "neat", "kash", "symphony", "koncurrent").forEach {
    includeBuild("../$it")
}

rootProject.name = "geo"

includeBuild("build-logic-geo")

includeSubs("geo", ".", "core", "languages", "countries", "fields")
