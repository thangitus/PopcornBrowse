plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            api(libs.koin.android)
            implementation(libs.room.runtime.android)
        }
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            api(libs.koin.core)

            api(libs.napier)

            api(libs.coil3.coil)
            api(libs.coil.compose)
            api(libs.coil.network.ktor)

            api(libs.voyager.screenmodel)

            api(libs.composeIcons.fontAwesome)

            api(compose.components.uiToolingPreview)
            api(compose.material)
            api(compose.material3)
            api(libs.kotlinx.datetime)
            api(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            api(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.app.movie.core"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    debugImplementation(libs.androidx.ui.tooling)
    add("kspAndroid", libs.room.compiler)

}

