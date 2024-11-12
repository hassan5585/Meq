package tech.mujtaba.meq.plugins.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class MeqCameraPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-camera2").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-core").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-view").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-extensions").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-lifecycle").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("androidx-camera-video").get())
            }
        }
    }
}