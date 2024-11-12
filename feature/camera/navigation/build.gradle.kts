plugins {
    id(libs.plugins.meq.library.base.get().pluginId)
}

android {
    namespace = "tech.mujtaba.meq.feature.camera.navigation"
}

dependencies {
    api(project(":core:navigation"))
}
