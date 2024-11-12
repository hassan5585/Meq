plugins {
    id(libs.plugins.meq.library.base.get().pluginId)
}

android {
    namespace = "tech.mujtaba.meq.feature.camera.domain"
}

dependencies {
    implementation(project(":core:util"))
}
