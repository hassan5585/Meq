package tech.mujtaba.meq.plugins.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class MeqTensorflowPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite-gpu").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite-support").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite-task-vision").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite-gpu-delegate-plugin").get())
                add(CONFIG_IMPLEMENTATION, libs.findLibrary("tensorflow-lite-metadata").get())
            }
        }
    }
}