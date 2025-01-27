@file:Suppress("UNUSED_VARIABLE")

val ktor_version: String by rootProject.project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"


kotlin {
    jvm("desktop") {
        jvmToolchain(11)

    }
    js(IR){
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation(compose.materialIconsExtended)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.3")
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
            }
        }
        val desktopTest by getting

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("io.ktor:ktor-client-js:$ktor_version")
            }
        }

        val jsTest by getting
    }
}