plugins {
    `kotlin-dsl`
}

kotlin {
   jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.jetbrains.kotlin.android.plugin)
    compileOnly(libs.jetbrains.kotlin.stdlib)
    compileOnly(libs.jetbrains.kotlin.dsl.plugin)
}

gradlePlugin {
    plugins {
        register("meqAppPlugin") {
            id = "tech.mujtaba.meq.plugin.app"
            implementationClass = "tech.mujtaba.meq.plugins.convention.MeqAppPlugin"
        }
        register("meqLibraryPlugin") {
            id = "tech.mujtaba.meq.plugin.library"
            implementationClass = "tech.mujtaba.meq.plugins.convention.MeqLibraryPlugin"
        }
        register("meqUiLibraryPlugin") {
            id = "tech.mujtaba.meq.plugin.library-ui"
            implementationClass = "tech.mujtaba.meq.plugins.convention.MeqUiLibraryPlugin"
        }
        register("meqCameraPlugin") {
            id = "tech.mujtaba.meq.plugin.camera"
            implementationClass = "tech.mujtaba.meq.plugins.convention.MeqCameraPlugin"
        }
        register("meqTensorflowPlugin") {
            id = "tech.mujtaba.meq.plugin.tensorflow"
            implementationClass = "tech.mujtaba.meq.plugins.convention.MeqTensorflowPlugin"
        }
    }
}