plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

val tmp = 2

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.core)
                api(asoft.viewmodel.core)
                api(asoft.koncurrent.later.core)
                api(asoft.identifier.core)
                api(asoft.kash.core)
                api(asoft.kotlinx.collections.interoperable)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(asoft.expect.coroutines)
                implementation(asoft.koncurrent.later.coroutines)
                implementation(asoft.live.test)
                implementation(asoft.koncurrent.primitives.mock)
            }
        }
    }
}