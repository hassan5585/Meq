plugins {
    id(libs.plugins.meq.library.ui.get().pluginId)
    id(libs.plugins.meq.camera.get().pluginId)
    id(libs.plugins.meq.tensorflow.get().pluginId)
}

android {
    namespace = "tech.mujtaba.meq.feature.camera.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":feature:camera:domain"))
    implementation(project(":feature:camera:navigation"))
}
