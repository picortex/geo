import geo.countries.GenerateCountriesTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("countries-generator")
}

description = "A kotlin multiplatform library"

val generateCountries by tasks.getting(GenerateCountriesTask::class)

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    targets.configureEach {
        compilations.all {
            compileKotlinTask.dependsOn(generateCountries)
        }
    }
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(generateCountries.outputDir)
            dependencies {
                api(projects.kashCurrency)
                api(kotlinx.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(projects.kommanderCoroutines)
            }
        }
    }
}