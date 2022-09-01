import geo.countries.GenerateCountriesTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("countries-generator")
}

val tmp = 2

val generateCountries by tasks.getting(GenerateCountriesTask::class)

kotlin {
    jvm { library() }
    js(IR) { library() }

    targets.configureEach {
        compilations.all {
            compileKotlinTask.dependsOn(generateCountries)
        }
    }
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(generateCountries.outputDir)
            dependencies {
                api(kotlinx.serialization.core)
                api(asoft.kash.currency)
                api(asoft.kotlinx.collections.interoperable)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}